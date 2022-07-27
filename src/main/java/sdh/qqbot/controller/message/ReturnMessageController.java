package sdh.qqbot.controller.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import sdh.qqbot.controller.database.UserController;
import sdh.qqbot.entity.database.User;
import sdh.qqbot.module.news.DailyNews;

/**
 * Go-CQ消息发送完成后，返回数据处理接口
 */
@Slf4j
public class ReturnMessageController {
    /**
     * 返回数据主入口
     *
     * @param json 返回数据json字符串
     */
    public static void MessageManager(String json) {
        JSONObject message = JSONObject.parseObject(json);
        try {
            String echo = message.getString("echo");
            HandleMessageByEcho(echo, message);
        } catch (Exception ignored) {

        }


    }

    private static void HandleMessageByEcho(String echo, JSONObject message) {
        switch (echo) {
            case "getGroupList":
                log.info(message.toString());
                JSONArray array = message.getJSONArray("data");
                DailyNews.sendDailyNewsByGroupList(array);
                break;
            case "getGroupMemberInfo":
                log.info(message.toString());
                String json = message.getString("data");
                User user = JSON.parseObject(json, User.class);
                UserController.addUser(user);
                break;
        }
    }
}
