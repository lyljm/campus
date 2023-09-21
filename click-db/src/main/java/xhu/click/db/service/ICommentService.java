package xhu.click.db.service;

import xhu.click.db.entity.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author eifying
 * @since 2023-09-17
 */
public interface ICommentService extends IService<Comment> {
    Map getCommentById(Long id);

    boolean isLiked(Long id);

    boolean likeComment(Long id);

    List getCommentByUserid(String id);
}
