package xhu.click.db.service;

import xhu.click.db.entity.pojo.Postliked;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author eifying
 * @since 2023-09-22
 */
public interface IPostlikedService extends IService<Postliked> {

    Long isLiked(Postliked postliked);

    boolean delete(Postliked postliked);
}
