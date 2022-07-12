package sdh.qqbot.module;

import lombok.extern.slf4j.Slf4j;
import sdh.qqbot.controller.QBotSendMessageController;
import sdh.qqbot.controller.SayingController;
import sdh.qqbot.entity.MessageEntity;
import sdh.qqbot.entity.SayingEntity;
import sdh.qqbot.utils.formatWeatherInfo;

import java.util.HashMap;
import java.util.Set;

/**
 * @author 语录查询模块
 */
@Slf4j
public class QuerySaying {
    private static HashMap<String, String> map = new HashMap<>();

    static {
        map.put("土味情话", "1001");
        map.put("精神语录", "1002");
        map.put("网易云热评", "1003");
        map.put("成人笑话", "1004");
        map.put("奇葩对话", "1005");
        map.put("舔狗日记", "1006");
        map.put("毒鸡汤", "1007");
        map.put("朋友圈文案", "1008");
        map.put("骂人宝典", "1009");
        map.put("动画", "2001");
        map.put("漫画", "2002");
        map.put("游戏", "2003");
        map.put("文学", "2004");
        map.put("原创", "2005");
        map.put("来自网络", "2006");
        map.put("其他", "2007");
        map.put("影视", "2008");
        map.put("诗词", "2009");
        map.put("网易云", "2010");
        map.put("哲学", "2011");
        map.put("抖机灵", "2012");

    }

    public static void sayingManager(MessageEntity message) {
        String[] msgArr = message.getMessage().split(" ");
        String say;
        switch (msgArr.length) {
            case 2:
                say = msgArr[1];
                querySaying(message, say, 1);
                break;
            case 3:
                say = msgArr[1];
                try {
                    int number = Integer.parseInt(msgArr[2]);
                    if (number > 10) {
                        number = 10;
                    }
                    querySaying(message, say, number);
                } catch (Exception e) {
                    QBotSendMessageController.sendMsg(message, "参数错误");
                }
                break;
        }
    }


    private static void querySaying(MessageEntity message, String say, Integer number) {
        if ("帮助".equals(say)) {
            QBotSendMessageController.sendMsg(message, help());
            return;
        }
        String type = map.get(say);
        if (type == null) {
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < number; i++) {
            SayingEntity sayingEntity = SayingController.querySaying(type);
            builder.append(sayingEntity.getText().replaceAll("<br>", "")).append("%0d");
        }
        SayingEntity sayingEntity = SayingController.querySaying(type);
        log.info(say + ":" + builder.toString());
        QBotSendMessageController.sendMsg(message, builder.toString());

    }

    private static String help() {
        Set<String> keys = map.keySet();
        StringBuilder builder = new StringBuilder();
        builder.append("语录帮助：").append("%0d");
        builder.append("语录 语录类型 [数量](最多十条)").append("%0d");
        for (String key : keys) {
            builder.append(key).append("%0d");
        }
        return builder.toString();
    }

}
