package sdh.qqbot.websocket;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import sdh.qqbot.controller.message.ReceiveMessageController;
import sdh.qqbot.controller.message.ReturnMessageController;
import sdh.qqbot.entity.message.MessageEntity;

/**
 * WS接收消息处理类
 *
 * @author SDH
 */
@Slf4j
public class WebSocketReceiveMessage implements IReceiveMessage {
    @Override
    public void onConnectSuccess() {

    }

    @Override
    public void onConnectFailed() {

    }

    @Override
    public void onClose() {

    }

    @Override
    public void onMessage(String text) {
        try {
            ReceiveMessageController.WSPostMessageManager(JSON.parseObject(text, MessageEntity.class));
        } catch (Exception e) {
            log.info(text);
            ReturnMessageController.MessageManager(text);
        }

    }
}
