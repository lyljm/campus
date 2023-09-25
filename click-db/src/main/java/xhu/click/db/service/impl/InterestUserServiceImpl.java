package xhu.click.db.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import xhu.click.common.utils.thread.LocalHolder;
import xhu.click.db.entity.dto.UserDto;
import xhu.click.db.entity.pojo.InterestUser;
import xhu.click.db.entity.pojo.User;
import xhu.click.db.mapper.InterestUserMapper;
import xhu.click.db.service.IInterestUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author eifying
 * @since 2023-09-23
 */
@Service
public class InterestUserServiceImpl extends ServiceImpl<InterestUserMapper, InterestUser> implements IInterestUserService {

    @Autowired
    InterestUserMapper interestUserMapper;

    @Override
    public boolean isInterest(String id) {
        UserDto userDto = (UserDto) LocalHolder.getObject();
        QueryWrapper<InterestUser> interestUserQueryWrapper = new QueryWrapper<>();
        interestUserQueryWrapper.eq("user_id", userDto.getId());
        interestUserQueryWrapper.eq("interest_user_id", id);
        int count = this.count(interestUserQueryWrapper);
        if(count>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean cancelInterest(String id) {
        UserDto userDto = (UserDto) LocalHolder.getObject();
        QueryWrapper<InterestUser> interestUserQueryWrapper = new QueryWrapper<>();
        interestUserQueryWrapper.eq("user_id", userDto.getId());
        interestUserQueryWrapper.eq("interest_user_id", id);
        boolean remove = this.remove(interestUserQueryWrapper);
        return remove;
    }

    @Override
    public boolean interestUser(String id) {
        UserDto userDto = (UserDto) LocalHolder.getObject();
        InterestUser interestUser = new InterestUser();
        interestUser.setUserId(userDto.getId());
        interestUser.setInterestUserId(id);
        boolean save = this.save(interestUser);
        return save;
    }

    @Override
    public List getInterestUser() {
        UserDto userDto = (UserDto) LocalHolder.getObject();
        List<User> list = interestUserMapper.listInterestUser(userDto.getId());
        List<UserDto> res = BeanUtil.copyToList(list, UserDto.class);
        return res;
    }
}
