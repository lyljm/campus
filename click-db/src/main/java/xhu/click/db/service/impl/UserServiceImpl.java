package xhu.click.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import xhu.click.db.entity.enums.UserStatus;
import xhu.click.db.entity.pojo.User;
import xhu.click.db.mapper.UserMapper;
import xhu.click.db.service.IInterestUserService;
import xhu.click.db.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author eifying
 * @since 2023-09-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    IInterestUserService interestUserService;


    /**
     * 通过openid获取用户
     *
     * @param openid
     * @return
     */
    @Override
    public User getByOpenid(User openid) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", openid.getOpenid());
        User user1 = userMapper.selectOne(queryWrapper);
        return user1;
    }


    @Override
    public boolean deleteByOpenid(String openid) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(openid != null && "".equals(openid.trim()), User::getOpenid, openid);
        boolean update = this.update().set("status", UserStatus.CANCEL.getStatus()).set("deleted", 1).update();
        return update;
    }

    @Override
    public boolean interestUser(String id) {
        boolean interest = interestUserService.isInterest(id);
        // 已经关注
        if (interest) {
            // 取消关注
            interestUserService.cancelInterest(id);
            return false;
        } else {
            // 关注
            interestUserService.interestUser(id);
            return true;
        }
    }
}
