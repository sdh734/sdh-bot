package sdh.qqbot;

import okhttp3.WebSocket;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sdh.qqbot.utils.WSUtil;

@SpringBootApplication
public class AppApplication {
    WebSocket webSocket = WSUtil.getInstance();

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

}
