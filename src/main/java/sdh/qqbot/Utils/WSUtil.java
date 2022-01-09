package sdh.qqbot.Utils;

import okhttp3.*;
import okio.ByteString;
import sdh.qqbot.Controller.ReceiveMessageController;

public class WSUtil {
    String url = "ws://127.0.0.1:6700/";
    Request request = new Request.Builder().get().url(url).build();
    private WebSocket websocket = null;
    private OkHttpClient client = OkHttpInstance.getInstance();
    private WSUtil() {
        //开始连接
        websocket = client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
                //连接成功...
                Log.i("连接成功");
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                super.onMessage(webSocket, text);
                //收到消息...（一般是这里处理json）
                ReceiveMessageController.MessageClassification(text);
//                LOG.i(text);
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                super.onMessage(webSocket, bytes);
                //收到消息...（一般很少这种消息）
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);
                //连接关闭...
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable throwable, Response response) {
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
