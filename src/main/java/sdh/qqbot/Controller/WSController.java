package sdh.qqbot.Controller;

import okhttp3.*;
import okio.ByteString;
import org.springframework.web.bind.annotation.RestController;
import sdh.qqbot.Utils.Log;
import sdh.qqbot.Utils.OkHttpInstance;

@RestController
public class WSController {
    String url = "ws://127.0.0.1:6700/";
    Request request = new Request.Builder().get().url(url).build();
    static WebSocket websocket = null;
    private OkHttpClient client = OkHttpInstance.getInstance();

    public WebSocket getInstance() {
        if (websocket != null) {
            return websocket;
        } else {
            startWS();
            return websocket;
        }
    }

    private void startWS() {
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
                Log.i("连接失败，重新连接中...");
                for (int i = 0; i < 3; i++) {
                    Log.i("尝试第“+i+”次重新连接");
                    startWS();
                }
            }
        });
    }
}
