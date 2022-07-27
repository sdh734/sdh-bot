package sdh.qqbot.module.picture;

import lombok.extern.slf4j.Slf4j;
import sdh.qqbot.controller.api.QueryApiManagerController;
import sdh.qqbot.controller.message.QBotSendMessageController;
import sdh.qqbot.entity.api.TwoDimensionalSpaceEntity;
import sdh.qqbot.entity.message.MessageEntity;

/**
 * 二次元图
 * @author fusheng
 */
@Slf4j
public class TwoDimensionalSpace {

    /**
     * 二次元图管理器
     * @param message 消息实体
     */
    public static void twoDimensionalSpaceEntity(MessageEntity message){
        queryTwoDimensionalSpaceEntity(message);
    }

    /**
     * 查询二次元图
     * @param message 消息实体
     */
    private static void queryTwoDimensionalSpaceEntity(MessageEntity message){
        TwoDimensionalSpaceEntity twoDimensionalSpaceEntity = QueryApiManagerController.queryTwoDimensionalSpace();
        String url = twoDimensionalSpaceEntity.getImgUrl();
        log.info("二次元链接：" + url);
        String cqMsg = "[CQ:image,file=picture,c=3,url=" + url + "]\n原图链接：" + url;
        QBotSendMessageController.sendMsg(message,cqMsg,null);
    }

}
