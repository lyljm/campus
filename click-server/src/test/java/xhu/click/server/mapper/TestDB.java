package xhu.click.server.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xhu.click.db.mapper.UserMapper;

import java.util.List;

@SpringBootTest
public class TestDB {
    @Autowired
    UserMapper userMapper;

    @Test
    void TestUser() {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        List list = userMapper.selectList(queryWrapper);
        System.out.println(list);

    }
}
