package sdh.qqbot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sdh.qqbot.utils.Log;

import java.time.LocalDateTime;

@SpringBootTest
class AppApplicationTests {

    @Test
    public void testDrwa() {
        Log.i(LocalDateTime.parse("2021-11-22T12:00").toString());
    }
}
