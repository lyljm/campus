package xhu.click.db.service.impl;

import xhu.click.db.entity.pojo.Admin;
import xhu.click.db.mapper.AdminMapper;
import xhu.click.db.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author eifying
 * @since 2023-09-17
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
