package sdh.qqbot.module;

import lombok.extern.slf4j.Slf4j;
import sdh.qqbot.controller.api.QueryApiManagerController;
import sdh.qqbot.controller.message.QBotSendMessageController;
import sdh.qqbot.entity.api.message.MessageEntity;

/**
 * 每日一言
 * @author fusheng
 */
@Slf4j
public class AWordADay {

    public static void aWordADayManager(MessageEntity message) {
        queryAWordADay(message);
    }

    private static void queryAWordADay(MessageEntity message){
        String aWordADay = QueryApiManagerController.queryAWordADay();
        String word = aWordADay.replaceAll("</p>", "").replaceAll("<p>", "");
        log.info("每日一言：" + word);
        QBotSendMessageController.sendMsg(message, word);
    }
}
