package sdh.qqbot.module.news;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sdh.qqbot.controller.api.QueryApiManagerController;
import sdh.qqbot.controller.message.QBotSendMessageController;
import sdh.qqbot.entity.api.DailyNewsEntity;
import sdh.qqbot.entity.message.MessageEntity;
import sdh.qqbot.entity.message.SendMessageEntity;

/**
 * 每日新闻模块 每日定时发送 关键词触发
 *
 * @author SDH
 */
@Component
@Slf4j
public class DailyNews {
    /**
     * 早报功能入口
     *
     * @param message 消息实体类
     */
    public static void dailyNewsManager(MessageEntity message) {
        DailyNewsEntity dailyNewsEntity = queryDailyNews();
        QBotSendMessageController.sendMsg(message, "[CQ:image,file=picture,c=3,url=" + dailyNewsEntity.getData().getImage() + "]", null);
    }

    /**
     * 查询每日早报信息
     *
     * @return 每日早报实体类
     */
    private static DailyNewsEntity queryDailyNews() {
        return QueryApiManagerController.queryDailyNews();
    }

    /**
     * 定时发送每日早报
     * 每天8：30发送
     * 机器人在的群都会发送
     */
    @Async
    @Scheduled(cron = "0 30 8 * * ?")
    public void sendDailyNewsScheduledTask() {
        SendMessageEntity msg = new SendMessageEntity();
        msg.setAction("get_group_list");
        msg.setEcho("getGroupList");
        QBotSendMessageController.sendMsgBySendMessageEntity(msg);
    }

    public static void sendDailyNewsByGroupList(JSONArray array) {
        DailyNewsEntity dailyNewsEntity = queryDailyNews();
        String url = dailyNewsEntity.getData().getImage();
        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            QBotSendMessageController.sendGroupMsg(jsonObject.getString("group_id"), "[CQ:image,file=picture,c=3,url=" + url + "]", null);
        }
    }
}
