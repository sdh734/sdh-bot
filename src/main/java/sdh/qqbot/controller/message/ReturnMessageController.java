package sdh.qqbot.controller.message;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import sdh.qqbot.module.news.DailyNews;

/**
 * Go-CQ消息发送完成后，返回数据处理接口
 */
public class ReturnMessageController {
    /**
     * 返回数据主入口
     *
     * @param json 返回数据json字符串
     */
    public static void MessageManager(String json) {
        JSONObject message = JSONObject.parseObject(json);
        String echo = message.getString("echo");
        HandleMessageByEcho(echo, message);
    }

    private static void HandleMessageByEcho(String echo, JSONObject message) {
        switch (echo) {
            case "getGroupList":
                JSONArray array = message.getJSONArray("data");
                DailyNews.sendDailyNewsByGroupList(array);
                break;
        }
    }
}
