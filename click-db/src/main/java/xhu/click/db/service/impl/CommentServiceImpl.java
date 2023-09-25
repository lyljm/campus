package xhu.click.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import xhu.click.common.entity.constants.RedisConstants;
import xhu.click.common.utils.thread.LocalHolder;
import xhu.click.db.entity.dto.UserDto;
import xhu.click.db.entity.pojo.Comment;
import xhu.click.db.mapper.CommentMapper;
import xhu.click.db.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author eifying
 * @since 2023-09-17
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;


    /**
     * 通过postid获取评论id
     *
     * @param id
     * @return
     */
    @Override
    public Map getCommentById(Long id) {
        Map<Comment, List> res = new HashMap<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("fatherid", id);
        List<Comment> list = commentMapper.selectList(queryWrapper);
        list.forEach(comment -> {
            QueryWrapper childQueryWrapper = new QueryWrapper();
            childQueryWrapper.eq("fatherid", comment.getId());
            List childList = commentMapper.selectList(childQueryWrapper);
            res.put(comment, childList);
        });
        return res;
    }

    /**
     * 根据评论id判断是否已经点赞
     */
    @Override
    public boolean isLiked(Long id) {
        UserDto userDto = (UserDto) LocalHolder.getObject();
        return stringRedisTemplate.opsForSet().isMember(RedisConstants.COMMENT_IS_LIKED + id, userDto.getId());
    }


    /**
     * 点赞评论
     *
     * @param id
     * @return
     */
    @Override
    public boolean likeComment(Long id) {
        UserDto userDto = (UserDto) LocalHolder.getObject();
        boolean liked = isLiked(id);
        String key = RedisConstants.COMMENT_IS_LIKED + id;
        //已经点赞
        if (liked) {
            boolean isSuccess = this.update().setSql("liked=liked-1").eq("id", id).update();
            stringRedisTemplate.opsForSet().remove(key, userDto.getId());
        } else {
//            未点赞
            boolean isSuccess = this.update().setSql("liked=liked+1").eq("id", id).update();
            stringRedisTemplate.opsForSet().add(key, userDto.getId());
        }
        return !liked;
    }

    @Override
    public List getCommentByUserid(String id) {
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("user_id",id);
        List<Comment> comments = commentMapper.selectList(commentQueryWrapper);
        return comments;
    }

}
