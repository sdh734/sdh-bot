package sdh.qqbot.module.picture;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import sdh.qqbot.config.ApiKeyConfig;
import sdh.qqbot.config.ApiUrlConfig;
import sdh.qqbot.config.ResolutionConfig;
import sdh.qqbot.controller.message.QBotSendMessageController;
import sdh.qqbot.entity.message.MessageEntity;
import sdh.qqbot.entity.api.wallhaven.WallHavenInfo;
import sdh.qqbot.entity.api.wallhaven.WallHavenSearchResult;
import sdh.qqbot.utils.okhttp.OkHttpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取壁纸模块
 * 包含2个功能：
 * 1. 每日必应壁纸获取
 * 2. WallHaven网站壁纸获取。
 *
 * @author SDh
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
                WallHavenSearchResult result1 = searchWallpaper(null, 1, null);
                sendWallPaperBySearchResult(message, result1);
                break;
            case 2:
                if ("帮助".equals(msgArr[1]) || "help".equals(msgArr[1])) {
                    String stringBuilder = "壁纸命令帮助" + "\n------------\n" +
                            "壁纸 无参数，默认群聊发送3张，私聊发送24张。分辨率最低1920x1080" +
                            "\n------------\n" +
                            "壁纸 必应（Bing|bing|必应壁纸） 发送每日必应图片" +
                            "\n------------\n" +
                            "壁纸 参数 参数可以是英文关键词，也可以是分辨率参数（2K|4K|8K），也可以是数字（数字即返回搜索结果指定页面的数据，不建议写太大的）" +
                            "\n------------\n" +
                            "关键词参数无数量限制，分辨率参数最多包含3个，数字参数只能一个。" +
                            "\n------------\n";
                    QBotSendMessageController.sendMsg(message, stringBuilder,null);
                } else if ("bing".equals(msgArr[1]) || "Bing".equals(msgArr[1]) || "必应".equals(msgArr[1]) || "必应壁纸".equals(msgArr[1])) {
                    String bingWallPaperURL = getBingWallPaper();
                    log.info("Bing壁纸链接：" + bingWallPaperURL);
                    bingWallPaperURL = bingWallPaperURL.substring(0, bingWallPaperURL.indexOf("&"));
                    log.info("转义后的Bing壁纸链接：" + bingWallPaperURL);
                    String cqMsg = "[CQ:image,file=Bing,url=" + bingWallPaperURL + "]";
                    log.info("转义后的CQ码：" + cqMsg);
                    QBotSendMessageController.sendMsg(message, cqMsg,null);
                } else if (!"-1".equals(matchDisplay(msgArr[1]))) {
                    //第二个参数为分辨率参数
                    List<String> resolution = new ArrayList<>();
                    resolution.add(matchDisplay(msgArr[1]));
                    WallHavenSearchResult result2 = searchWallpaper(null, 1, resolution);
                    sendWallPaperBySearchResult(message, result2);
                } else {
                    //第二个参数为关键词参数
                    WallHavenSearchResult result2 = searchWallpaper(msgArr[1], 1, null);
                    sendWallPaperBySearchResult(message, result2);
                }
                break;
            default:
                //三个参数及以上的方法
                int page = 1;//默认页码为第一页
                StringBuilder tag = new StringBuilder();//关键词参数集合
                List<String> resolution1 = new ArrayList<>();
                try {
                    //判断最后一位参数是否为数字，是就修改页码。
                    page = Integer.parseInt(msgArr[msgArr.length - 1]);
                    //循环判断每个参数信息
                    for (int i = 1; i < msgArr.length - 1; i++) {
                        //判断是否是分辨率参数
                        if (!"-1".equals(matchDisplay(msgArr[i]))) {
                            //分辨率参数
                            resolution1.add(matchDisplay(msgArr[i]));
                        } else {
                            //非分辨率参数
                            tag.append(msgArr[i]).append(",");
                        }

                    }
                } catch (Exception ignored) {
                    //最后一位参数非数字，判断不是页标参数
                    //循环判断每个参数信息
                    for (int i = 1; i < msgArr.length; i++) {
                        if (!"-1".equals(matchDisplay(msgArr[i]))) {
                            //分辨率参数
                            resolution1.add(matchDisplay(msgArr[i]));
                        } else {
                            //非分辨率参数
                            tag.append(msgArr[i]).append(",");
                        }
                    }
                }
                String substring = tag.substring(0, tag.lastIndexOf(","));//处理多余逗号
                WallHavenSearchResult result3 = searchWallpaper(substring, page, resolution1);
                sendWallPaperBySearchResult(message, result3);
                break;
        }
    }

    /**
     * 根据参数匹配分辨率 支持1K、2K、4K、8K。
     *
     * @param str 参数文本
     * @return 对应参数的分辨率数据 如果不匹配则返回-1
     */
    private static String matchDisplay(String str) {
        String pattern = "\\d[kK]";
        if (str.matches(pattern)) {
            //匹配到为分辨率参数
            //匹配2K
            if (str.matches("^(2k|2K)$")) {
                return ResolutionConfig.K2;
            }
            //匹配4K
            if (str.matches("^(4k|4K)$")) {
                return ResolutionConfig.K4;
            }
            //匹配8K
            if (str.matches("^(8k|8K)$")) {
                return ResolutionConfig.K8;
            }
        }
        return "-1";
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
                String cqMsg = "[CQ:image,file=Wallpaper,url=" + result.getData().get(i).getThumbs().getSmall() + ",cache=0,subType=0,id=40000]\n";
                cqMsg += "原图链接：" + wallpaperURL;
                log.info("壁纸消息：" + cqMsg);
                QBotSendMessageController.sendMsg(message, cqMsg,null);
            }
        } else {
            for (int i = 0; i < 3; i++) {
                String wallpaperURL = result.getData().get(i).getPath();
                String cqMsg = "[CQ:image,file=Wallpaper,url=" + result.getData().get(i).getThumbs().getSmall() + ",cache=0,subType=0,id=40000]\n";
                cqMsg += "原图链接：" + wallpaperURL;
                log.info(cqMsg);
                QBotSendMessageController.sendMsg(message, cqMsg,null);
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
     * 通过搜索参数、页标，获取搜索结果，默认不限制分辨率。
     *
     * @param tag  搜索参数
     * @param page 页标
     * @return WallHaven搜索结果实体对象，包含24个图片结果
     */
    private static WallHavenSearchResult searchWallpaper(String tag, int page, @Nullable List<String> resolution) {
        String json;
        if (tag == null && resolution == null) {
            json = OkHttpUtil.get(ApiUrlConfig.WALLHAVEN_BASE_SEARCH_URL);
        } else if (tag != null && resolution == null) {
            json = OkHttpUtil.get(ApiUrlConfig.WALLHAVEN_BASE_SEARCH_URL + "?q=" + tag + "&page=" + page);
        } else if (tag == null) {
            String request = parserListToString(resolution);
            json = OkHttpUtil.get(ApiUrlConfig.WALLHAVEN_BASE_SEARCH_URL + "?resolutions=" + request + "&page=" + page);
        } else {
            String request = parserListToString(resolution);
            json = OkHttpUtil.get(ApiUrlConfig.WALLHAVEN_BASE_SEARCH_URL + "?q=" + tag + "&resolutions=" + request + "&page=" + page);
        }
        return JSON.parseObject(json, WallHavenSearchResult.class);
    }

    /**
     * 将分辨率集合转化为请求参数的格式
     *
     * @param list 分辨率集合
     * @return 请求参数的格式
     */
    private static String parserListToString(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : list) {
            stringBuilder.append(s);
            stringBuilder.append(",");
        }
        return stringBuilder.substring(0, stringBuilder.lastIndexOf(","));
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
