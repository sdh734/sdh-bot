package sdh.qqbot.module;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sdh.qqbot.config.ApiUrlConfig;
import sdh.qqbot.controller.QBotSendMessageController;
import sdh.qqbot.controller.UserController;
import sdh.qqbot.dao.Sexpicture;
import sdh.qqbot.entity.ImgUrlEntity;
import sdh.qqbot.entity.MessageEntity;
import sdh.qqbot.mapper.SexpictureMapper;
import sdh.qqbot.utils.OkHttpUtil;

import javax.annotation.PostConstruct;

/**
 * 发送色图模块
 *
 * @author SDH
 */

@Component
@Slf4j
public class sexPicture {
    static SexpictureMapper sexpictureMapper;
    @Autowired
    private SexpictureMapper iSexpictureMapper;

    public static void sendSexPicture(MessageEntity message) {
        String[] msgArray = message.getMessage().split(" ");
        if ("帮助".equals(msgArray[1])) {
            QBotSendMessageController.sendMsg(message, help());
            return;
        }
        StringBuilder baseUrl = setUrlTag(message, msgArray);
        log.info("请求Api链接：" + baseUrl);
        if (baseUrl != null) {
            String imgUrl;
            String url = OkHttpUtil.get(baseUrl.toString());
            ImgUrlEntity img = JSON.parseObject(url, ImgUrlEntity.class);
            if (img.getData().size() != 0) {
                ImgUrlEntity.DataDTO dataDTO = img.getData().get((int) Math.floor(Math.random() * img.getData().size()));
                imgUrl = dataDTO.getUrls().getSmall();
                log.info("色图图片链接：" + imgUrl);
                String cqMsg = "[CQ:image,file=picture,c=3,url=" + imgUrl + "]%0d原图链接：" + dataDTO.getUrls().getOriginal();
                QBotSendMessageController.sendMsg(message, cqMsg);
                for (int i = 0; i < img.getData().size(); i++) {
                    sdh.qqbot.dao.Sexpicture sexpicture = new Sexpicture();
                    sexpicture.setPictureId(img.getData().get(i).getPid().toString());
                    sexpicture.setPictureUrl(img.getData().get(i).getUrls().getOriginal());
                    if (!sexpictureMapper.exists(new QueryWrapper<Sexpicture>().eq("picture_id", sexpicture.getPictureId()))) {
                        sexpictureMapper.insert(sexpicture);
                    }
                }
            } else {
                String errorMessage = "找不到关键词";
                QBotSendMessageController.sendMsg(message, errorMessage);
            }
        } else {
            String errorMessage = "无R18权限";
            QBotSendMessageController.sendMsg(message, errorMessage);
        }

    }

    private static StringBuilder setUrlTag(MessageEntity message, String[] msgArray) {
        StringBuilder baseUrl = new StringBuilder(ApiUrlConfig.ST_URL);
        //R18 Flag
        boolean r18;
        int userType = UserController.getPermissionByUserId(message.getUserId());
        //根据数组长度判断是否传入其他参数
        switch (msgArray.length) {
            case 1:
                break;
            case 2:
                r18 = "r18".equals(msgArray[1]) || "R18".equals(msgArray[1]);
                if (r18 && userType > 1) {
                    baseUrl.append("&r18=1");
                } else if (r18 && userType == 1) {
                    baseUrl = null;
                } else {
                    baseUrl.append("&tag=").append(msgArray[1]);
                }
                break;
            default:
                r18 = "r18".equals(msgArray[1]) || "R18".equals(msgArray[1]);
                if (r18 && userType > 1) {
                    baseUrl.append("&r18=1");
                } else if (r18 && userType == 1) {
                    baseUrl = null;
                }
                if (baseUrl != null) {
                    for (int i = 2; i < msgArray.length; i++) {
                        baseUrl.append("&tag=").append(msgArray[i]);
                    }
                }

        }
        return baseUrl;
    }

    @PostConstruct
    public void init() {
        sexpictureMapper = iSexpictureMapper;
    }

    public static String help() {
        StringBuilder builder = new StringBuilder();
        builder.append("色图帮助：").append("%0d");
        builder.append("色图 [关键词]（数量不限） [r18](可选，权限找机器人管理员开)");
        return builder.toString();
    }
}
