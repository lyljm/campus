package xhu.click.db.entity.query;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

@ApiModel
@Data
public class PageStrategy {
    @ApiModelProperty("当前页")
    private Long cur;
    @ApiModelProperty("每页大小")
    Long size;
    @ApiModelProperty("主题")
    Long subject;
    @ApiModelProperty("排序策略，0：最新，1，热度，")
    Long strategy;
}
