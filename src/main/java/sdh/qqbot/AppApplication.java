package sdh.qqbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import sdh.qqbot.websocket.WebSocketManager;
import sdh.qqbot.websocket.WebSocketReceiveMessage;

@SpringBootApplication(scanBasePackages = {"sdh.qqbot"})
@EnableScheduling
@EnableAsync
public class AppApplication {
    //启动WS连接
//    WebSocket webSocket = WebSocketClient.getInstance().getWebSocket();
    private static final WebSocketManager webSocketManager = WebSocketManager.getInstance();

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
        webSocketManager.init(new WebSocketReceiveMessage());
    }

}
