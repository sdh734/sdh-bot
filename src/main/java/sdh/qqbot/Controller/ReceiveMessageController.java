package sdh.qqbot.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RestController;
import sdh.qqbot.Entity.MessageEntity;
import sdh.qqbot.Module.drawPrize;
import sdh.qqbot.Module.sexPicture;
import sdh.qqbot.Utils.Log;

@RestController
public class ReceiveMessageController {

    public static void MessageClassification(String message) {
        //将cqHttp上报内容字符串转化为JSON对象
        JSONObject text = JSON.parseObject(message);
        /*
          获取上报消息类型
          message 聊天消息
          event 事件消息
         */
        String postType = text.getString("post_type");
        switch (postType) {
            case "message":
                /*
                    聊天消息类型
                    private 私聊
                    group 群聊
                */

                if ("private".equals(text.getString("message_type"))) {
                    PrivateMessageManager(JSON.parseObject(message, MessageEntity.class));
                } else {
                    GroupMessageManager(JSON.parseObject(message, MessageEntity.class));
                }
                break;
            case "event":
                break;
        }
    }

    /**
     * 私聊消息处理
     *
     * @param message 私聊消息实体
     */
    private static void PrivateMessageManager(MessageEntity message) {
        UserController.addUser(message);
        Log.i("收到私聊消息，消息内容：" + message.getMessage());
        String[] msgArray = message.getMessage().split(" ");
        if ("色图".equals(msgArray[0])) {
            sexPicture.sendSexPicture(message, "private");
        } else if ("抽奖".equals(msgArray[0])) {
            drawPrize.DrawPrizeManager(message, "private");
        }
    }

    /**
     * 群聊消息
     *
     * @param message 群聊消息实体
     */
    private static void GroupMessageManager(MessageEntity message) {
        UserController.addUser(message);
        Log.i("收到群聊消息，消息内容：" + message.getMessage());
        String[] msgArray = message.getMessage().split(" ");
        switch (msgArray[0]) {
            case "色图":
                sexPicture.sendSexPicture(message, "group");
                break;
            case "抽奖":
                drawPrize.DrawPrizeManager(message, "group");
                break;
            default:
        }
    }
}
