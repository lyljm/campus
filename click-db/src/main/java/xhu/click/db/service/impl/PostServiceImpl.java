package xhu.click.db.service.impl;

import xhu.click.db.entity.pojo.Post;
import xhu.click.db.mapper.PostMapper;
import xhu.click.db.service.IPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
