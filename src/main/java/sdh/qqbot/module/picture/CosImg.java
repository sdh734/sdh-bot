package sdh.qqbot.module.picture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import sdh.qqbot.controller.api.QueryApiManagerController;
import sdh.qqbot.controller.message.QBotSendMessageController;
import sdh.qqbot.entity.message.MessageEntity;

/**
 * cos图片
 *
 * @author fusheng
 */
@Slf4j
public class CosImg {

    /**
     * cos图片管理器
     *
     * @param message 消息实体
     */
    public static void cosImgManager(MessageEntity message) {
        queryCosImg(message);
    }

    /**
     * 查询cos图片
     *
     * @param message 消息实体
     */
    private static void queryCosImg(MessageEntity message) {
        String url = QueryApiManagerController.queryCosImg();
        if (StringUtils.hasText(url)) {
            log.info("cos链接：" + url);
            url = url.substring(0, url.length() - 1);
            String cqMsg = "[CQ:image,file=picture,c=3,url=" + url + "]";
            QBotSendMessageController.sendMsg(message, cqMsg, null);
        }
    }
}
