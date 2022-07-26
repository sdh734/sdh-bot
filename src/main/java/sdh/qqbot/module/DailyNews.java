package sdh.qqbot.module;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sdh.qqbot.controller.api.QueryApiManagerController;
import sdh.qqbot.controller.message.QBotSendMessageController;
import sdh.qqbot.entity.api.DailyNewsEntity;
import sdh.qqbot.entity.api.message.MessageEntity;
import sdh.qqbot.utils.OkHttpUtil;

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
        QBotSendMessageController.sendMsg(message, "[CQ:image,file=picture,c=3,url=" + dailyNewsEntity.getData().getImage() + "]");
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
        DailyNewsEntity dailyNewsEntity = queryDailyNews();
        String json = OkHttpUtil.get("http://127.0.0.1:5700/get_group_list");
        JSONObject parse = JSONObject.parseObject(json);
        JSONArray array = parse.getJSONArray("data");
        array.forEach((object) -> {
            JSONObject object1 = (JSONObject) object;
            QBotSendMessageController.sendGroupMsg(object1.getString("group_id"), "[CQ:image,file=picture,c=3,url=" + dailyNewsEntity.getData().getImage() + "]");
        });

    }
}
