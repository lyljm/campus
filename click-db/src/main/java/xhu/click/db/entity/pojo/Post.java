package xhu.click.db.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
@TableName("campus_post")
@ApiModel(value="Post对象", description="post表")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "postid")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "主题id")
    @TableField("subject_id")
    private Long subjectId;

    @ApiModelProperty(value = "内容")
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
    @TableField("add_time")
    private LocalDateTime addTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
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
