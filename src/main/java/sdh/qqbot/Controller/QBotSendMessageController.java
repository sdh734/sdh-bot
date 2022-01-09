package sdh.qqbot.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sdh.qqbot.Entity.MessageEntity;
import sdh.qqbot.Utils.OkHttpUtil;

import java.util.List;

@RestController
public class QBotSendMessageController {
    private static StringBuilder baseUrl = new StringBuilder("http://127.0.0.1:5700/");

    @GetMapping("/test")
    public void Test() {
        //URL %0d 回车符
        OkHttpUtil.get("http://127.0.0.1:5700/send_private_msg?user_id=1247769958&message=1111111111111111111111111111111" + "%0d" + "2222222222222222222222222222222222222222222222222%0d333");
    }

    @PostMapping("/cqhttp")
    public void cqhttp(String messqge) {
        System.out.println(messqge);
    }

    public static void sendPrivateMsg(String userId, String message) {
        baseUrl.append("send_msg?");
        baseUrl.append("user_id=").append(userId);
        baseUrl.append("&message=").append(message);
        send(baseUrl.toString());
    }

    public static void sendGroupMsg(String groupId, String message) {
        baseUrl.append("send_msg?");
        baseUrl.append("group_id=").append(groupId);
        baseUrl.append("&message=").append(message);
        send(baseUrl.toString());
    }

    public static void sendMsg(MessageEntity messageEntity, String message) {
        if ("private".equals(messageEntity.getMessageType())) {
            sendPrivateMsg(messageEntity.getUserId().toString(), message);
        } else {
            sendGroupMsg(messageEntity.getGroupId().toString(), message);
        }
    }

    private static void send(String url) {
        OkHttpUtil.get(url);
        baseUrl = new StringBuilder("http://127.0.0.1:5700/");
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
