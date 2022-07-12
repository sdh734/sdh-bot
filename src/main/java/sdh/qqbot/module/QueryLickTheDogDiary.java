package sdh.qqbot.module;

import lombok.extern.slf4j.Slf4j;
import sdh.qqbot.controller.LickTheDogDiaryController;
import sdh.qqbot.controller.QBotSendMessageController;
import sdh.qqbot.entity.LickTheDogDiaryEntity;
import sdh.qqbot.entity.MessageEntity;
import java.util.HashMap;
import java.util.List;

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
        LickTheDogDiaryEntity lickTheDogDiaryEntity = LickTheDogDiaryController.queryLickTheDogDiary();
        if (lickTheDogDiaryEntity == null ){
            return;
        }
        List<HashMap<String, String>> newsList = lickTheDogDiaryEntity.getNewsList();
        if (newsList  == null || newsList.size() == 0){
            return;
        }
        log.info("舔狗日记：" + newsList.get(0).get("content"));
        QBotSendMessageController.sendMsg(message,newsList.get(0).get("content") );
    }
}
