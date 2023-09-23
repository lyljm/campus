package xhu.click.db.service;

import xhu.click.db.entity.pojo.Collect;
import com.baomidou.mybatisplus.extension.service.IService;
import xhu.click.db.entity.pojo.Postliked;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author eifying
 * @since 2023-09-22
 */
public interface ICollectService extends IService<Collect> {

    Long isCollect(Collect collect);

    boolean delete(Collect collect);
}
