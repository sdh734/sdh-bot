package sdh.qqbot.module;

import sdh.qqbot.controller.message.QBotSendMessageController;
import sdh.qqbot.controller.api.QueryApiManagerController;
import sdh.qqbot.entity.api.GUSHIEntity;
import sdh.qqbot.entity.api.message.MessageEntity;

import java.util.HashMap;
import java.util.Set;

/**
 * 古诗词一言模块
 *
 * @author SDH
 */
public class GUSHICI {
    private static final HashMap<String, String> type = new HashMap<>();

    static {
        type.put("全部", "all");
        type.put("抒情", "shuqing");
        type.put("四季", "siji");
        type.put("山水", "shanshui");
        type.put("天气", "tianqi");
        type.put("人物", "renwu");
        type.put("人生", "rensheng");
        type.put("生活", "shenghuo");
        type.put("节日", "jieri");
        type.put("动物", "dongwu");
        type.put("植物", "zhiwu");
        type.put("食物", "shiwu");
    }

    /*
      古诗词一言模块主入口
     */
    public static void gushiManager(MessageEntity message) {
        String[] msgArr = message.getMessage().split(" ");
        if (msgArr.length > 1 && "帮助".equals(msgArr[1])) {
            QBotSendMessageController.sendMsg(message, help());
            return;
        }
        if (msgArr.length > 1 && msgArr[1] != null) {
            GUSHIEntity gushiEntity = QueryApiManagerController.queryGUSHI(type.get(msgArr[1]));
            QBotSendMessageController.sendMsg(message, gushiEntity.toString());
        } else {
            GUSHIEntity gushiEntity = QueryApiManagerController.queryGUSHI(type.get("全部"));
            QBotSendMessageController.sendMsg(message, gushiEntity.toString());
        }
    }

    /**
     * 古诗词一言帮助
     *
     * @return 帮助文本
     */
    private static String help() {
        Set<String> keys = type.keySet();
        StringBuilder builder = new StringBuilder();
        builder.append("古诗词一言帮助：").append("%0d");
        builder.append("古诗词 古诗类型(可选，默认全部，具体查看下表：)").append("%0d");
        for (String key : keys) {
            builder.append(key).append("%0d");
        }
        return builder.toString();
    }
}
