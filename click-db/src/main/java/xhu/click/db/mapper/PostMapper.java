package xhu.click.db.mapper;

import org.apache.ibatis.annotations.Mapper;
import xhu.click.db.entity.pojo.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * post表 Mapper 接口
 * </p>
 *
 * @author eifying
 * @since 2023-09-17
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {

}
