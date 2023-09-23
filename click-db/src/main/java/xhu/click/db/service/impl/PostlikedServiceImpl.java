package xhu.click.db.service.impl;

import cn.hutool.core.io.unit.DataUnit;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import xhu.click.db.entity.pojo.Postliked;
import xhu.click.db.mapper.PostlikedMapper;
import xhu.click.db.service.IPostlikedService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author eifying
 * @since 2023-09-22
 */
@Service
public class PostlikedServiceImpl extends ServiceImpl<PostlikedMapper, Postliked> implements IPostlikedService {
    @Override
    public Long isLiked(Postliked postliked) {
        QueryWrapper<Postliked> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id", postliked.getPostId());
        queryWrapper.eq("user_id", postliked.getUserId());
        Postliked one = getOne(queryWrapper);
        if (one == null)
            return 0L;
        return one.getAddTime().toEpochSecond(ZoneOffset.of("+8"));
    }

    @Override
    public boolean delete(Postliked postliked) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("post_id", postliked.getPostId());
        queryWrapper.eq("user_id", postliked.getUserId());
        return this.remove(queryWrapper);
    }
}
