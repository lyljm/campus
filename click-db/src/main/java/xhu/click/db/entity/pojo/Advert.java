package xhu.click.db.entity.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 广告
 * </p>
 *
 * @author eifying
 * @since 2023-09-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("campus_advert")
@ApiModel(value="Advert对象", description="广告")
public class Advert implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "广告id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "父级id，可能是post，也可能是评论，为0是广告")
    @TableField("fatherid")
    private Long fatherid;

    @ApiModelProperty(value = "回复的人的id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "被回复的的人的id")
    @TableField("replay_id")
    private Long replayId;

    @ApiModelProperty(value = "跳转链接")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "图片地址")
    @TableField("photo_url")
    private String photoUrl;

    @ApiModelProperty(value = "点赞数")
    @TableField("like")
    private Long like;

    @ApiModelProperty(value = "评论数")
    @TableField("comment")
    private Long comment;

    @ApiModelProperty(value = "收藏数")
    @TableField("collect")
    private Long collect;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "add_time",fill = FieldFill.INSERT)    private LocalDateTime addTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除")
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;

    @ApiModelProperty(value = "更新版本号")
    @TableField("version")
    @Version
    private Integer version;


}