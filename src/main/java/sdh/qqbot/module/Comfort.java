package sdh.qqbot.module;

import lombok.extern.slf4j.Slf4j;
import sdh.qqbot.controller.api.QueryApiManagerController;
import sdh.qqbot.controller.message.QBotSendMessageController;
import sdh.qqbot.entity.api.message.MessageEntity;

/**
 * 安慰语录
 *
 * @author fusheng
 */
@Slf4j
public class Comfort {

    public static void comfortManager(MessageEntity message) {
        queryComfort(message);
    }

    private static void queryComfort(MessageEntity message) {
        String json = QueryApiManagerController.queryComfort();
        String comfort = json.substring(json.indexOf("━━━━━━━━━") + 10, json.lastIndexOf("━━━━━━━━━") - 1);
        log.info("安慰语录：" + comfort);
        QBotSendMessageController.sendMsg(message, comfort);
    }
}
