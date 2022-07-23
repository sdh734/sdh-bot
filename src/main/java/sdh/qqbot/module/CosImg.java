package sdh.qqbot.module;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import sdh.qqbot.controller.api.QueryApiManagerController;
import sdh.qqbot.controller.message.QBotSendMessageController;
import sdh.qqbot.entity.api.message.MessageEntity;

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
     * @param message
     */
    public static void cosImgManager(MessageEntity message) {
        queryCosImg(message);
    }

    /**
     * 查询cos图片
     *
     * @param message
     */
    private static void queryCosImg(MessageEntity message) {
        String url = QueryApiManagerController.queryCosImg();
        if (StringUtils.hasText(url)) {
            log.info("cos链接：" + url);
            String cqMsg = "[CQ:image,file=picture,c=3,url=" + url + "]";
            QBotSendMessageController.sendMsg(message, cqMsg);
        }
    }
}
