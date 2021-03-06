package sdh.qqbot.controller.message;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sdh.qqbot.entity.api.MessageEntity;
import sdh.qqbot.utils.OkHttpUtil;
import sdh.qqbot.websocket.WebSocketManager;

import java.util.List;

/**
 * 与GO-CQ联动，发送消息接口
 *
 * @author SDH
 */
@RestController
public class QBotSendMessageController {
    private static final StringBuilder baseUrl = new StringBuilder("http://127.0.0.1:5700/send_msg");
    private static final WebSocketManager webSocketManager = WebSocketManager.getInstance();

    @GetMapping("/test")
    public void Test() {
        //URL %0d 回车符
//        OkHttpUtil.get("http://127.0.0.1:5700/send_private_msg?user_id=1604014795&message=1111111111111111111111111111111" + "%0d" + "2222222222222222222222222222222222222222222222222%0d333");
    }

    public static void sendPrivateMsg(String userId, String message) {
        String url = baseUrl.toString();
        url += "?user_id=" + userId;
        url += "&message=" + message;
        send(url);
    }

    public static void sendGroupMsg(String groupId, String message) {
        String url = baseUrl.toString();
        url += "?group_id=" + groupId;
        url += "&message=" + message;
        send(url);
    }

    public static void sendMsg(MessageEntity messageEntity, String message) {
        if ("private".equals(messageEntity.getMessageType())) {
            sendPrivateMsg(messageEntity.getUserId(), message);
        } else {
            sendGroupMsg(messageEntity.getGroupId(), message);
        }
    }

    private static void send(String url) {
        OkHttpUtil.get(url);
    }

    public static <T> StringBuilder generatorMessageByList(List<T> list) {
        StringBuilder stringBuilder = new StringBuilder();
        if (list != null) {
            for (T i : list) {
                stringBuilder.append(i.toString()).append("%0d");
            }
        }

        return stringBuilder;
    }
}
