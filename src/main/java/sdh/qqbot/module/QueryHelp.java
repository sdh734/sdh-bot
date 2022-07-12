package sdh.qqbot.module;

import sdh.qqbot.controller.QBotSendMessageController;
import sdh.qqbot.entity.MessageEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 帮助命令
 *
 * @author fusheng
 */
public class QueryHelp {
    private static final List<String> list = new ArrayList<>();

    static {
        list.add("抽奖");
        list.add("天气");
        list.add("壁纸");
        list.add("语录");
        list.add("每日一言");
        list.add("舔狗日记");
    }

    public static void helpManager(MessageEntity message) {
        queryHelp(message);
    }

    public static void queryHelp(MessageEntity message) {
        StringBuilder builder = new StringBuilder();
        builder.append("机器人功能：").append("%0d");
        if ("private".equals(message.getMessageType())) {
            builder.append("色图").append("%0d");
        }
        for (String function : list) {
            builder.append(function).append("%0d");
        }
        QBotSendMessageController.sendMsg(message, builder.toString());
    }
}
