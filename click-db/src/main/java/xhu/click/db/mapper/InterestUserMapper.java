package xhu.click.db.mapper;

import org.apache.ibatis.annotations.Mapper;
import xhu.click.db.entity.dto.UserDto;
import xhu.click.db.entity.pojo.InterestUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xhu.click.db.entity.pojo.User;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author eifying
 * @since 2023-09-23
 */
@Mapper
public interface InterestUserMapper extends BaseMapper<InterestUser> {
    List<User>listInterestUser(String id);
}
