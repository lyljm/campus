package xhu.click.server.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xhu.click.db.mapper.TestMapper;

@SpringBootTest
public class TestMappperTest {
    @Autowired
    TestMapper testMapper;

    @Test
    public void test() {
        System.out.println(testMapper.test());
    }
}
