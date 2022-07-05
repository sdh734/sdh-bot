package sdh.qqbot.module;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import sdh.qqbot.config.ApiKeyConfig;
import sdh.qqbot.config.ApiUrlConfig;
import sdh.qqbot.controller.QBotSendMessageController;
import sdh.qqbot.dao.WallHavenInfo;
import sdh.qqbot.dao.WallHavenSearchResult;
import sdh.qqbot.entity.MessageEntity;
import sdh.qqbot.utils.OkHttpUtil;

/**
 * 获取壁纸模块
 * 包含2个功能：
 * 1. 每日必应壁纸获取
 * 2. WallHaven网站壁纸获取。
 */
@Slf4j
public class wallpaperModule {
    /**
     * 根据接收的消息，判断参数等信息，并加以处理，返回对应壁纸数据。
     *
     * @param message 接收的消息实体对象
     */
    public static void wallpaperManager(MessageEntity message) {
        String[] msgArr = message.getMessage().split(" ");
        switch (msgArr.length) {
            case 1:
                WallHavenSearchResult result1 = searchWallpaper(null, 1);
                sendWallPaperBySearchResult(message, result1);
                break;
            case 2:
                if ("bing".equals(msgArr[1]) || "Bing".equals(msgArr[1]) || "必应".equals(msgArr[1]) || "必应壁纸".equals(msgArr[1])) {
                    String bingWallPaperURL = getBingWallPaper();
                    log.info("Bing壁纸链接：" + bingWallPaperURL);
                    bingWallPaperURL = bingWallPaperURL.substring(0, bingWallPaperURL.indexOf("&"));
                    log.info("转义后的Bing壁纸链接：" + bingWallPaperURL);
                    String cqMsg = "[CQ:image,file=Bing,url=" + bingWallPaperURL + "]";
                    log.info("转义后的CQ码：" + cqMsg);
                    QBotSendMessageController.sendMsg(message, cqMsg);
                } else {
                    WallHavenSearchResult result2 = searchWallpaper(msgArr[1], 1);
                    sendWallPaperBySearchResult(message, result2);
                }
                break;
            default:
                int page = 1;
                StringBuilder tag = new StringBuilder();
                try {
                    page = Integer.parseInt(msgArr[msgArr.length - 1]);
                    for (int i = 1; i < msgArr.length - 1; i++) {
                        tag.append(msgArr[i]);
                    }
                } catch (Exception ignored) {
                    for (int i = 1; i < msgArr.length; i++) {
                        tag.append(msgArr[i]);
                    }
                }
                WallHavenSearchResult result3 = searchWallpaper(String.valueOf(tag), page);
                sendWallPaperBySearchResult(message, result3);
                break;
        }
    }

    /**
     * 循环发送查到的壁纸。发送间隔1秒。
     *
     * @param result 搜索到的壁纸集合
     */
    private static void sendWallPaperBySearchResult(MessageEntity message, WallHavenSearchResult result) {
        if ("private".equals(message.getMessageType())) {
            for (int i = 0; i < result.getData().size(); i++) {
                String wallpaperURL = result.getData().get(i).getPath();
                String cqMsg = "[CQ:image,file=Wallpaper,url=" + result.getData().get(i).getThumbs().getSmall() + ",cache=0,subType=0,id=40000]%0d";
                cqMsg += "原图链接：" + wallpaperURL;
                log.info("壁纸消息：" + cqMsg);
                QBotSendMessageController.sendMsg(message, cqMsg);
            }
        } else {
            for (int i = 0; i < 3; i++) {
                String wallpaperURL = result.getData().get(i).getPath();
                String cqMsg = "[CQ:image,file=Wallpaper,url=" + result.getData().get(i).getThumbs().getSmall() + ",cache=0,subType=0,id=40000]%0d";
                cqMsg += "原图链接：" + wallpaperURL;
                log.info(cqMsg);
                QBotSendMessageController.sendMsg(message, cqMsg);
            }
        }
    }

    /**
     * 通过图片id获取图片详细信息
     *
     * @param id WallHaven图片ID
     * @return WallHaven图片信息类
     */
    private static WallHavenInfo getWallPaperInfoById(String id) {
        String INFO_URL = ApiUrlConfig.WALLHAVEN_BASE_INFO_URL + id + "?apikey=" + ApiKeyConfig.WALLHAVEN_KEY;
        String json = OkHttpUtil.get(INFO_URL);
        return JSON.parseObject(json, WallHavenInfo.class);
    }

    /**
     * 通过搜索参数、页标，获取搜索结果
     *
     * @param tag  搜索参数
     * @param page 页标
     * @return WallHaven搜索结果实体对象，包含24个图片结果
     */
    private static WallHavenSearchResult searchWallpaper(String tag, int page) {
        String json;
        if (tag == null) {
            json = OkHttpUtil.get(ApiUrlConfig.WALLHAVEN_BASE_SEARCH_URL);
        } else {
            json = OkHttpUtil.get(ApiUrlConfig.WALLHAVEN_BASE_SEARCH_URL + "?q=" + tag + "&page=" + page);
        }
        return JSON.parseObject(json, WallHavenSearchResult.class);
    }

    /**
     * 获取Bing每日壁纸的链接
     *
     * @return Bing每日壁纸的图片链接
     */
    private static String getBingWallPaper() {
        String json = OkHttpUtil.get("https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=zh-CN");
        JSONObject bing = JSONObject.parseObject(json);
        return "https://cn.bing.com" + bing.getJSONArray("images").getJSONObject(0).getString("url");
    }
}
