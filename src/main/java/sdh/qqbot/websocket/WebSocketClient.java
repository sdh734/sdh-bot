package sdh.qqbot.websocket;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import sdh.qqbot.config.ApiUrlConfig;
import sdh.qqbot.controller.message.ReceiveMessageController;
import sdh.qqbot.entity.message.MessageEntity;
import sdh.qqbot.utils.okhttp.OkHttpInstance;

/**
 * OkHttp连接WebSocket（已弃用）
 *
 * @author SDH
 */
@Slf4j
@Data
public class WebSocketClient {

    private static final WebSocketClient instance = new WebSocketClient();
    private WebSocket webSocket;

    private WebSocketClient() {
        String url = ApiUrlConfig.WS_URL;
        Request request = new Request.Builder().get().url(url).build();
        OkHttpClient client = OkHttpInstance.getInstance();

        webSocket = client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
                log.info("连接成功");
                //连接成功...
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
                ReceiveMessageController.WSPostMessageManager(JSON.parseObject(text, MessageEntity.class));
                //收到消息...（一般是这里处理json）
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
                super.onMessage(webSocket, bytes);
                //收到消息...（一般很少这种消息）
            }

            @Override
            public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                log.info("连接关闭");
                //连接关闭...
            }

            @Override
            public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable throwable, Response response) {
                log.info("连接失败");
                super.onFailure(webSocket, throwable, response);
                //连接失败...
            }
        });

    }

    public static WebSocketClient getInstance() {
        return instance;
    }
}
