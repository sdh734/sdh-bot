package sdh.qqbot.module;

import sdh.qqbot.controller.message.QBotSendMessageController;
import sdh.qqbot.entity.api.message.MessageEntity;

/**
 * 关于
 * @author fusheng
 */
public class AboutMe {

    /**
     * 关于我的管理器
     * @param message
     */
    public static void aboutMeManager(MessageEntity message){
        queryAboutMe(message);
    }

    private static void queryAboutMe(MessageEntity message){
        StringBuilder builder = new StringBuilder();
        builder.append("『Bot』").append("%0d");
        builder.append("版本：v2.0").append("%0d");
        builder.append("简介：基于go-cqhttp+SpringBoot开发的QQ机器人。").append("%0d");
        builder.append("项目地址：https://gitee.com/SDH734/sdh-bot/tree/master/").append("%0d");
        QBotSendMessageController.sendMsg(message, builder.toString());
    }
}
