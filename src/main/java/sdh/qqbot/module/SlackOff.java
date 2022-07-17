package sdh.qqbot.module;

import lombok.extern.slf4j.Slf4j;
import sdh.qqbot.controller.api.QueryApiManagerController;
import sdh.qqbot.controller.message.QBotSendMessageController;
import sdh.qqbot.entity.api.MessageEntity;
import sdh.qqbot.entity.api.SlackOffEntity;

import java.util.HashMap;

/**
 * 摸鱼人模块
 * @author fusheng
 */
@Slf4j
public class SlackOff {

    /**
     * 摸鱼管理模块
     * @param message
     */
    public static void slackOffManager(MessageEntity message){
        querySlackOff(message);
    }

    /**
     * 查询摸鱼图
     * @param message
     */
    private static void querySlackOff(MessageEntity message){
        SlackOffEntity slackOffEntity = QueryApiManagerController.querySlackOff();
        if (slackOffEntity != null){
            HashMap<String, String> data = slackOffEntity.getData();
            String url = data.get("moyu_url");
            log.info("摸鱼人链接：" + url);
            String cqMsg = "[CQ:image,file=picture,c=3,url=" + url + "]";
            QBotSendMessageController.sendMsg(message,cqMsg);
        }
    }
}
