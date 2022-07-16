package sdh.qqbot.module;

import lombok.extern.slf4j.Slf4j;
import sdh.qqbot.controller.message.QBotSendMessageController;
import sdh.qqbot.controller.api.QueryApiManagerController;
import sdh.qqbot.entity.api.LickTheDogDiaryEntity;
import sdh.qqbot.entity.api.MessageEntity;

/**
 * 舔狗日记查询
 * @author fusheng
 */
@Slf4j
public class QueryLickTheDogDiary {


    public static void diaryManager(MessageEntity message){
        queryLickTheDogDiary(message);
    }

    private static void queryLickTheDogDiary(MessageEntity message){
        LickTheDogDiaryEntity lickTheDogDiaryEntity = QueryApiManagerController.queryLickTheDogDiary();
        log.info("舔狗日记：" + lickTheDogDiaryEntity.getMsg());
        QBotSendMessageController.sendMsg(message,lickTheDogDiaryEntity.getMsg() );
    }
}
