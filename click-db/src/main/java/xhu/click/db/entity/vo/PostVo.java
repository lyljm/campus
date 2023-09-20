package xhu.click.db.entity.vo;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * post表
 * </p>
 *
 * @author eifying
 * @since 2023-09-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PostVo对象", description="图文")
public class PostVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "postid")
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "主题id")
    private Long subjectId;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "图片地址")
    private List<String> photos;

    @ApiModelProperty(value = "post状态，0草稿，1未审核，2已发布")
    private Integer status;

    @ApiModelProperty(value = "点赞数")
    private Long liked;

    @ApiModelProperty(value = "评论数")
    private Long comment;

    @ApiModelProperty(value = "收藏数")
    private Long collect;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime addTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}
