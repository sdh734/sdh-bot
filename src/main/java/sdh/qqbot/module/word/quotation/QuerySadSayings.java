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
        String[] split = sayings.split("\n");
        String substring = split[2].substring(0, split[2].length() - 1);
        log.info("伤感语录：" + substring);
        QBotSendMessageController.sendMsg(message, substring, null);
    }
}
