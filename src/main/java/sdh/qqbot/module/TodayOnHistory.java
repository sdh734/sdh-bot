package sdh.qqbot.module;

import lombok.extern.slf4j.Slf4j;
import sdh.qqbot.controller.QBotSendMessageController;
import sdh.qqbot.controller.TodayOnHistoryController;
import sdh.qqbot.entity.MessageEntity;
import sdh.qqbot.entity.TodayOnHistoryEntity;

import java.util.List;

/**
 * 历史上的今天模块
 *
 * @author fusheng
 */
@Slf4j
public class TodayOnHistory {

    /**
     * 历史上的今天管理模块
     * @param message
     */
    public static void todayOnHistoryManager(MessageEntity message) {
        queryHistory(message);
    }

    /**
     * 历史上的今天查询
     * @param message
     */
    private static void queryHistory(MessageEntity message) {
        TodayOnHistoryEntity todayOnHistoryEntity = TodayOnHistoryController.queryTodayOnHistoryEntity();
        StringBuilder builder = new StringBuilder();
        builder.append("日期：" + todayOnHistoryEntity.getDay()).append("%0d");
        List<String> contents = todayOnHistoryEntity.getContent();
        for (int i = 0; i < contents.size(); i++) {
            String content = contents.get(i);
            builder.append(content);
            if (i < contents.size() -1) {
                builder.append("%0d");
            }
        }
        QBotSendMessageController.sendMsg(message, builder.toString());
    }
}
