package xhu.click.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import xhu.click.common.entity.constants.RedisConstants;
import xhu.click.common.utils.thread.LocalHolder;
import xhu.click.db.entity.dto.UserDto;
import xhu.click.db.entity.enums.PostStatus;
import xhu.click.db.entity.enums.PostStrategy;
import xhu.click.db.entity.vo.PageResult;
import xhu.click.db.entity.pojo.Post;
import xhu.click.db.mapper.PostMapper;
import xhu.click.db.service.IPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * post表 服务实现类
 * </p>
 *
 * @author eifying
 * @since 2023-09-17
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {
    @Autowired
    PostMapper postMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 获取图文
     *
     * @param cur
     * @param size
     * @param subject
     * @param strategy
     * @return
     */
    @Override
    public PageResult getPostPage(int cur, int size, int subject, int strategy) {
        QueryWrapper<Post> lambdaQueryWrapper = new QueryWrapper<>();
        lambdaQueryWrapper.eq("subject_id", subject);
//        已发布
        lambdaQueryWrapper.eq("status", PostStatus.POSTED.getStatus());
        /**
         *  排序策略
         */
        if (strategy == PostStrategy.LATEST.getStatus()) {
            lambdaQueryWrapper.orderByAsc("add_time");
        } else if (strategy == PostStrategy.Hottest.getStatus()) {
            lambdaQueryWrapper.orderByDesc("liked");
        } else if (strategy == PostStrategy.RESENT_REPLY.getStatus()) {
            lambdaQueryWrapper.orderByDesc("update_time");
        }
//        分页查询
        PageHelper.startPage(cur, size);
        List<Post> posts = postMapper.selectList(lambdaQueryWrapper);
        PageInfo<Post> postPageInfo = new PageInfo<>(posts);
        long total = postPageInfo.getTotal();
        List<Post> list = postPageInfo.getList();
        //封装pageResult
        PageResult pageResult = new PageResult();
        pageResult.setCurrent(cur);
        pageResult.setTotal(total);
        pageResult.setPageSize(size);
        pageResult.setList(list);
        return pageResult;
    }

    /**
     * 删除图文
     *
     * @param id
     * @return 0, 1
     */
    @Override
    public int deletePostById(Long id) {
        UserDto userDto = (UserDto) LocalHolder.getObject();
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userDto.getId());
        queryWrapper.eq("id", id);
        return postMapper.delete(queryWrapper);
    }

    /**
     * 是否已经点赞图文
     *
     * @param id
     * @return
     */
    @Override
    public boolean isLiked(Long id) {
        UserDto userDto = (UserDto) LocalHolder.getObject();
        return stringRedisTemplate.opsForSet().isMember(RedisConstants.IS_LIKED + id, userDto.getId());
    }

    @Override
    public boolean likedPost(Long id) {
        UserDto userDto = (UserDto) LocalHolder.getObject();
        boolean liked = isLiked(id);
        String key=RedisConstants.IS_LIKED+id;
        //已经点赞
        if(liked){
            boolean isSuccess = this.update().setSql("liked=liked-1").eq("id", id).update();
            stringRedisTemplate.opsForSet().remove(key,userDto.getId());
        }else{
//            未点赞
            boolean isSuccess = this.update().setSql("liked=liked+1").eq("id", id).update();
            stringRedisTemplate.opsForSet().add(key,userDto.getId());
        }
        return !liked;
    }


}
