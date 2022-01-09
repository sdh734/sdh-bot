package sdh.qqbot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sdh.qqbot.Utils.Log;

import java.time.LocalDateTime;

@SpringBootTest
class AppApplicationTests {
    @Test
    public void testSelect() {
//        DrawprizeController.joinDrawPrize(1,"1247769958","private","1247769958");
    }

    @Test
    public void testDrwa() {
//        DrawprizeController.joinDrawPrize(1,"1247769958","private","1247769958");
        // DrawprizeController.manualDrawPrize(1, "private", "1247769958");
        Log.i(LocalDateTime.parse("2021-11-22T12:00").toString());
    }
}
