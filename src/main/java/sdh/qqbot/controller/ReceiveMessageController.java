package sdh.qqbot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sdh.qqbot.entity.MessageEntity;
import sdh.qqbot.module.drawPrize;
import sdh.qqbot.module.queryWeather;
import sdh.qqbot.module.sexPicture;
import sdh.qqbot.utils.Log;

@RestController
public class ReceiveMessageController {


    @PostMapping("/push")
    public static void MessageClassification(@RequestBody MessageEntity message) {

        /*
          获取上报消息类型
          message 聊天消息
          event 事件消息
         */

        switch (message.getPostType()) {
            case "message":
                /*
                    聊天消息类型
                    private 私聊
                    group 群聊
                */
                Log.i(message.toString());
                if ("private".equals(message.getMessageType())) {
                    PrivateMessageManager(message);
                } else {
                    GroupMessageManager(message);
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
        switch (msgArray[0]) {
            case "色图":
                sexPicture.sendSexPicture(message, "private");
                break;
            case "抽奖":
                drawPrize.DrawPrizeManager(message);
                break;
            case "天气":
                queryWeather.weatherManager(message);
                break;
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
                //sexPicture.sendSexPicture(message, "group");
                break;
            case "抽奖":
                drawPrize.DrawPrizeManager(message);
                break;
            case "天气":
                queryWeather.weatherManager(message);
                break;
            default:
        }
    }
}
