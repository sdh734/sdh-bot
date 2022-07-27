package sdh.qqbot.controller.message;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sdh.qqbot.entity.message.MessageEntity;
import sdh.qqbot.entity.message.SendMessageEntity;
import sdh.qqbot.websocket.WebSocketManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 与GO-CQ联动，发送消息接口
 *
 * @author SDH
 */
@RestController
public class QBotSendMessageController {
    private static final WebSocketManager webSocketManager = WebSocketManager.getInstance();

    @GetMapping("/test")
    public void Test() {
        //URL \n 回车符
//        OkHttpUtil.get("http://127.0.0.1:5700/send_private_msg?user_id=1604014795&message=1111111111111111111111111111111" + "\n" + "2222222222222222222222222222222222222222222222222\n333");
        SendMessageEntity msg = new SendMessageEntity();
        msg.setAction("get_group_list");
        msg.setEcho("getGroupList");
        send(msg.toString());
    }

    public static void sendPrivateMsg(String userId, String message, String echo) {
        SendMessageEntity msg = new SendMessageEntity();
        msg.setAction("send_msg");
        msg.setEcho(echo);
        Map<String, String> params = new HashMap<>();
        params.put("user_id", userId);
        params.put("message", message);
        msg.setParams(params);
        send(msg.toString());
    }

    public static void sendGroupMsg(String groupId, String message, String echo) {
        SendMessageEntity msg = new SendMessageEntity();
        msg.setAction("send_msg");
        msg.setEcho(echo);
        Map<String, String> params = new HashMap<>();
        params.put("group_id", groupId);
        params.put("message", message);
        msg.setParams(params);
        send(msg.toString());
    }

    public static void sendMsg(MessageEntity messageEntity, String message, String echo) {
        if ("private".equals(messageEntity.getMessageType())) {
            sendPrivateMsg(messageEntity.getUserId(), message, echo);
        } else {
            sendGroupMsg(messageEntity.getGroupId(), message, echo);
        }
    }

    public static void sendMsgBySendMessageEntity(SendMessageEntity message) {
        send(message.toString());
    }

    private static void send(String json) {
        webSocketManager.sendMessage(json);
    }

    public static <T> StringBuilder generatorMessageByList(List<T> list) {
        StringBuilder stringBuilder = new StringBuilder();
        if (list != null) {
            for (T i : list) {
                stringBuilder.append(i.toString()).append("\n");
            }
        }

        return stringBuilder;
    }
}
