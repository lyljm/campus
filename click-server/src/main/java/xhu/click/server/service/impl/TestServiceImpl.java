package xhu.click.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import xhu.click.common.entity.constants.RedisConstants;
import xhu.click.db.mapper.TestMapper;
import xhu.click.server.service.TestService;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestMapper testMapper;

    @Override
    @Cacheable(value = RedisConstants.REDIS_PREFIX+"test")
    public int testService() {
        return testMapper.test();
    }
}
