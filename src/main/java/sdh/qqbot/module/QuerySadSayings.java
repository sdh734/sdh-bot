package sdh.qqbot.module;

import lombok.extern.slf4j.Slf4j;
import sdh.qqbot.controller.QBotSendMessageController;
import sdh.qqbot.controller.SadSayingsController;
import sdh.qqbot.entity.MessageEntity;

/**
 * 伤感语录
 * @author fusheng
 */
@Slf4j
public class QuerySadSayings {

    /**
     * 伤感语录管理器
     */
    public static void sadSayingsManager(MessageEntity message){
        querySadSayings(message);
    }

    private static void querySadSayings(MessageEntity message){
        String sayings = SadSayingsController.querySadSayings();
        String substring = sayings.substring(sayings.indexOf("━━━━━━━━━") + 9, sayings.lastIndexOf("━━━━━━━━━"));
        log.info("伤感语录：" + substring);
        QBotSendMessageController.sendMsg(message,substring );
    }
}
