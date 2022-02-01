package sdh.qqbot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sdh.qqbot.mapper.UserMapper;

@SpringBootTest
class AppApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    public void test1() {
    }
}
