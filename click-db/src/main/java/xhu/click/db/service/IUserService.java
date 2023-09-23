package xhu.click.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xhu.click.db.entity.pojo.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author eifying
 * @since 2023-09-17
 */
public interface IUserService extends IService<User> {
    User getByOpenid(User openid);

    int deleteByOpenid(String openid);

    int interestUser(Long id);
}
