package sdh.qqbot.utils;

import okhttp3.*;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

/**
 * 弃用WS连接
 */
public class WSUtil {
    final String url = "ws://127.0.0.1:6700/";
    final Request request = new Request.Builder().get().url(url).build();
    private final WebSocket websocket;

    private WSUtil() {
        //开始连接
        OkHttpClient client = OkHttpInstance.getInstance();
        websocket = client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
                super.onOpen(webSocket, response);
                //连接成功...
                Log.i("连接成功");
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
                super.onMessage(webSocket, text);
                //收到消息...（一般是这里处理json）
                // ReceiveMessageController.MessageClassification(text);

            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
                super.onMessage(webSocket, bytes);
                //收到消息...（一般很少这种消息）
            }

            @Override
            public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                super.onClosed(webSocket, code, reason);
                //连接关闭...
            }

            @Override
            public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable throwable, Response response) {
                super.onFailure(webSocket, throwable, response);
                //连接失败...
            }
        });
    }

    private static class WSUtilInstance {
        private static final WSUtil INSTANCE = new WSUtil();
    }

    public static WebSocket getInstance() {
        return WSUtilInstance.INSTANCE.websocket;
    }
}
