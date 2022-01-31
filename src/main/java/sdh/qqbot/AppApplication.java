package sdh.qqbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"sdh.qqbot"})
@EnableScheduling
public class AppApplication {
    //WebSocket webSocket = WSUtil.getInstance();

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

}
