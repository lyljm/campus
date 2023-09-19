package xhu.click.db.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xhu.click.db.entity.pojo.User;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author eifying
 * @since 2023-09-17
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
