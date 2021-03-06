package sdh.qqbot.websocket;

import com.alibaba.fastjson.JSON;
import sdh.qqbot.controller.message.ReceiveMessageController;
import sdh.qqbot.entity.api.MessageEntity;

/**
 * WS接收消息处理类
 *
 * @author SDH
 */
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
        ReceiveMessageController.WSPostMessageManager(JSON.parseObject(text, MessageEntity.class));
    }
}
