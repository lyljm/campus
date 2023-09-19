package xhu.click.db.mapper;

import org.apache.ibatis.annotations.Mapper;
import xhu.click.db.entity.pojo.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 主题表 Mapper 接口
 * </p>
 *
 * @author eifying
 * @since 2023-09-17
 */
@Mapper
public interface SubjectMapper extends BaseMapper<Subject> {

}
