package sdh.qqbot.module.picture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import sdh.qqbot.controller.api.QueryApiManagerController;
import sdh.qqbot.controller.message.QBotSendMessageController;
import sdh.qqbot.entity.message.MessageEntity;

/**
 * 妹纸图片
 *
 * @author fusheng
 */
@Slf4j
public class TheGirlImg {

    public static void theGirlImgManager(MessageEntity message) {
        queryTheGirlImg(message);
    }

    private static void queryTheGirlImg(MessageEntity message) {
        String url = QueryApiManagerController.queryTheGirlImg();
        if (StringUtils.hasText(url)) {
            log.info("妹纸图：" + url);
            String cqMsg = "[CQ:image,file=picture,c=3,url=" + url + "]";
            QBotSendMessageController.sendMsg(message, cqMsg,null);
        }
    }

}
