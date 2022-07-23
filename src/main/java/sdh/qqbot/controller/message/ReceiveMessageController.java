package sdh.qqbot.controller.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sdh.qqbot.controller.database.UserController;
import sdh.qqbot.entity.api.message.MessageEntity;
import sdh.qqbot.module.*;

/**
 * 接收消息接口
 *
 * @author SDH
 */
@Slf4j
@RestController
public class ReceiveMessageController {

    /**
     * HTTP POST反报接收消息接口 已弃用
     *
     * @param message 由Spring自动装箱的消息实体对象
     */
    @PostMapping("/push")
    public static void HTTPPostMessageManager(@RequestBody MessageEntity message) {
        /*
          获取上报消息类型
          message 聊天消息
          event 事件消息
         */
        SwitchMessageByType(message);
    }

    /**
     * WS连接上报消息处理接口
     */
    public static void WSPostMessageManager(MessageEntity message) {
        /*
          获取上报消息类型
          message 聊天消息
          event 事件消息
         */
        SwitchMessageByType(message);
    }

    /**
     * 通过传入的消息实体对象，判断消息类别，并分别转至不同功能模块处理
     *
     * @param message 消息实体对象
     */
    private static void SwitchMessageByType(MessageEntity message) {
        switch (message.getPostType()) {
            case "message"://消息类型
                /*
                    聊天消息类型
                    private 私聊
                    group 群聊
                */
                log.info(message.toString());
                MessageManager(message);
                break;
            case "event"://事件类型
                break;
        }
    }

    /**
     * ；聊天消息处理
     *
     * @param message 聊天消息实体
     */
    private static void MessageManager(MessageEntity message) {
        UserController.addUser(message);
        log.info("收到私聊消息，消息内容：" + message.getMessage());
        String[] msgArray = message.getMessage().split(" ");
        switch (msgArray[0]) {
            case "色图":
                if ("private".equals(message.getMessageType())) {
                    //私聊开启色图
                    SexPicture.sendSexPicture(message);
                } else {
                    QBotSendMessageController.sendMsg(message, "群聊未开启此功能。");
                }
                break;
            case "抽奖":
                DrawPrize.DrawPrizeManager(message);
                break;
            case "天气":
                queryWeather.weatherManager(message);
                break;
            case "壁纸":
                wallpaperModule.wallpaperManager(message);
                break;
            case "语录":
                QuerySaying.sayingManager(message);
                break;
            case "每日一言":
                AWordADay.aWordADayManager(message);
                break;
            case "古诗词":
                GUSHICI.gushiManager(message);
                break;
            case "舔狗日记":
                QueryLickTheDogDiary.diaryManager(message);
                break;
            case "历史上的今天":
                TodayOnHistory.todayOnHistoryManager(message);
                break;
            case "到点了":
                QuerySadSayings.sadSayingsManager(message);
                break;
            case "早报":
                DailyNews.dailyNewsManager(message);
                break;
            case "二次元":
                TwoDimensionalSpace.twoDimensionalSpaceEntity(message);
                break;
            case "撩人":
                Prologue.prologueManager(message);
                break;
            case "摸鱼":
                SlackOff.slackOffManager(message);
                break;
            case "疫情":
                NcovInfo.NcovInfoManager(message);
                break;
            case "帮助":
                QueryHelp.helpManager(message);
                break;
            default:
                //复读机
                if ("group".equals(message.getMessageType())) {
                    Repeater.repeaterManager(message);
                }
        }
    }

}
