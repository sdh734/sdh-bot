package sdh.qqbot.module.word.quotation;

import lombok.extern.slf4j.Slf4j;
import sdh.qqbot.controller.api.QueryApiManagerController;
import sdh.qqbot.controller.message.QBotSendMessageController;
import sdh.qqbot.entity.message.MessageEntity;

/**
 * 伤感语录
 *
 * @author fusheng
 */
@Slf4j
public class QuerySadSayings {

    /**
     * 伤感语录管理器
     */
    public static void sadSayingsManager(MessageEntity message) {
        querySadSayings(message);
    }

    private static void querySadSayings(MessageEntity message) {
        String sayings = QueryApiManagerController.querySadSayings();
        String substring = sayings.substring(sayings.indexOf("━━━━━━━━━") + 10, sayings.lastIndexOf("━━━━━━━━━"));
        log.info("伤感语录：" + substring);
        QBotSendMessageController.sendMsg(message, substring, null);
    }
}
