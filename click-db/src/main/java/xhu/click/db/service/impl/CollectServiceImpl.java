package xhu.click.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import xhu.click.db.entity.pojo.Collect;
import xhu.click.db.entity.pojo.Postliked;
import xhu.click.db.mapper.CollectMapper;
import xhu.click.db.service.ICollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author eifying
 * @since 2023-09-22
 */
@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements ICollectService {

    @Override
    public Long isCollect(Collect collect) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id", collect.getPostId());
        queryWrapper.eq("user_id", collect.getUserId());
        Collect one = getOne(queryWrapper);
        if (one == null)
            return 0L;
        return one.getAddTime().toEpochSecond(ZoneOffset.of("+8"));
    }

    @Override
    public boolean delete(Collect collect) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("post_id", collect.getPostId());
        queryWrapper.eq("user_id", collect.getUserId());
        return this.remove(queryWrapper);
    }
}
