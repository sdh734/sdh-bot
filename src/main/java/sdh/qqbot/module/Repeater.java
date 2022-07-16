package sdh.qqbot.module;

import sdh.qqbot.controller.QBotSendMessageController;
import sdh.qqbot.entity.MessageEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 复读机模块
 */
public class Repeater {

    private static Map<Integer, String> repeaterUtterance = new HashMap<>();

    private static Map<String, List<String>> talk = new ConcurrentHashMap<>();

    static {
        repeaterUtterance.put(7, "打断施法");
        repeaterUtterance.put(10, "一群人天天只知道复读");
    }

    /**
     * 复读机管理模块
     * @param message
     */
    public static void repeaterManager(MessageEntity message) {
        repeater(message);
    }

    /**
     * 复读机复读
     * @param message
     */
    public static void repeater(MessageEntity message) {
        String groupId = message.getGroupId();
        if (groupId == null){
            return;
        }
        List<String> list = talk.get(groupId);
        if (list == null) {
            list = new ArrayList<>();
            list.add(message.getMessage());
            talk.put(groupId, list);
            return;
        }
        boolean contains = list.contains(message.getMessage());
        if (contains) {
            list.add(message.getMessage());
        } else {
            list.clear();
            list.add(message.getMessage());
        }
        int size = list.size();
        String speak = null;
        speak = repeaterUtterance.get(size);
        if (size == 3) {
            speak = message.getMessage();
        }
        if (speak != null) {
            QBotSendMessageController.sendMsg(message, speak);
        }
    }
}
