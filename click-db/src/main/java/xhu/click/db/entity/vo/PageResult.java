package xhu.click.db.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("PageResult分页结果")
public class PageResult<T> {
    /**
     * 页数
     */
    @ApiModelProperty("总共的页数")
    Long total;
    /**
     * 当前页数
     */
    @ApiModelProperty("当前页数")
    int current;
    /**
     * 每页大小
     */
    @ApiModelProperty("每页大小")
    int pageSize;
    /**
     * 数据
     */
    @ApiModelProperty("分页数据")
    List<T> list;
}
