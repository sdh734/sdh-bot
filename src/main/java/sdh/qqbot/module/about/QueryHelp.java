package sdh.qqbot.module.about;

import sdh.qqbot.controller.message.QBotSendMessageController;
import sdh.qqbot.entity.message.MessageEntity;

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
        list.add("古诗词");
        list.add("每日一言");
        list.add("舔狗日记");
        list.add("古诗词");
        list.add("历史上的今天");
        list.add("到点了");
        list.add("二次元");
        list.add("摸鱼");
        list.add("早报");
        list.add("疫情");
        list.add("撩人");
        list.add("cos");
        list.add("点歌");
        list.add("早安语录");
        list.add("妹纸图");
        list.add("安慰语录");
    }

    public static void helpManager(MessageEntity message) {
        queryHelp(message);
    }

    public static void queryHelp(MessageEntity message) {
        StringBuilder builder = new StringBuilder();
        builder.append("机器人功能：").append("\n");
        if ("private".equals(message.getMessageType())) {
            builder.append("色图").append("\n");
        }
        for (int i = 0; i < list.size(); i++) {
            String function = list.get(i);
            builder.append(function);
            if (i < list.size() - 1){
                builder.append("\n");
            }
        }
        QBotSendMessageController.sendMsg(message, builder.toString(),null);
    }
}
