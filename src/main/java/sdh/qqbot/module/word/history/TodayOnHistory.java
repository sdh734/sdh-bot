package sdh.qqbot.module.word.history;

import lombok.extern.slf4j.Slf4j;
import sdh.qqbot.controller.api.QueryApiManagerController;
import sdh.qqbot.controller.message.QBotSendMessageController;
import sdh.qqbot.entity.api.TodayOnHistoryEntity;
import sdh.qqbot.entity.message.MessageEntity;

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
     * @param message 消息实体类
     */
    public static void todayOnHistoryManager(MessageEntity message) {
        queryHistory(message);
    }

    /**
     * 历史上的今天查询
     * @param message 消息实体类
     */
    private static void queryHistory(MessageEntity message) {
        TodayOnHistoryEntity todayOnHistoryEntity = QueryApiManagerController.queryTodayOnHistoryEntity();
        StringBuilder builder = new StringBuilder();
        builder.append("日期：").append(todayOnHistoryEntity.getDay()).append("\n");
        List<String> contents = todayOnHistoryEntity.getContent();
        for (int i = 0; i < contents.size(); i++) {
            String content = contents.get(i);
            builder.append(content);
            if (i < contents.size() -1) {
                builder.append("\n");
            }
        }
        QBotSendMessageController.sendMsg(message, builder.toString(),null);
    }
}
