package sdh.qqbot.module;

import lombok.extern.slf4j.Slf4j;
import sdh.qqbot.controller.api.QueryApiManagerController;
import sdh.qqbot.controller.message.QBotSendMessageController;
import sdh.qqbot.entity.api.message.MessageEntity;

/**
 * @author fusheng
 */
@Slf4j
public class GoodMorning {

    public static void goodMorningManager(MessageEntity message) {
        queryGoodMorning(message);
    }

    private static void queryGoodMorning(MessageEntity message) {
        String json = QueryApiManagerController.queryGoodMorning();
        String goodMorning = json.substring(json.lastIndexOf("±") + 2, json.lastIndexOf("━━━━━━━━━") - 1);
        log.info("早安语录：" + goodMorning);
        QBotSendMessageController.sendMsg(message, goodMorning);
    }
}
