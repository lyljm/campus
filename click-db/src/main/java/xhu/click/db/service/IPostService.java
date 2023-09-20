package xhu.click.db.service;

import org.springframework.web.multipart.MultipartFile;
import xhu.click.db.entity.dto.PostDto;
import xhu.click.db.entity.vo.PageResult;
import xhu.click.db.entity.pojo.Post;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * post表 服务类
 * </p>
 *
 * @author eifying
 * @since 2023-09-17
 */
public interface IPostService extends IService<Post> {

    PageResult getPostPage(int cur, int size, int subject, int strategy);

    int deletePostById(Long id);

    boolean isLiked(Long id);

    boolean likedPost(Long id);


    boolean uploadPost(PostDto dto, MultipartFile[] files);
}
