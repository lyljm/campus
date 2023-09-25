package xhu.click.db.service;

import xhu.click.db.entity.pojo.InterestUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author eifying
 * @since 2023-09-23
 */
public interface IInterestUserService extends IService<InterestUser> {

    boolean isInterest(String id);

    boolean cancelInterest(String id);

    boolean interestUser(String id);

    List getInterestUser();
}
