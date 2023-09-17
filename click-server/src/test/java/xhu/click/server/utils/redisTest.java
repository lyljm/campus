package xhu.click.server.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xhu.click.common.utils.RedisIdWorker;

@SpringBootTest
public class redisTest {
    @Autowired
    RedisIdWorker redisIdWroker;
    @Test
    void redisIDWorkTest(){
        System.out.println(redisIdWroker.nextId("test"));
    }
}
