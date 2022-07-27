package sdh.qqbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import sdh.qqbot.websocket.WebSocketManager;

/**
 * QQBot主程序入口。
 *
 * @author SDH fusheng
 */
@SpringBootApplication(scanBasePackages = {"sdh.qqbot"})
@EnableScheduling
@EnableAsync
public class AppApplication {
    private static final WebSocketManager webSocketManager = WebSocketManager.getInstance();
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
        webSocketManager.init();//启动WS连接
    }

}
