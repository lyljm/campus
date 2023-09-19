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
 * 主题表
 * </p>
 *
 * @author eifying
 * @since 2023-09-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("campus_subject")
@ApiModel(value="Subject对象", description="主题表")
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主题id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "主题名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "主题图片")
    @TableField("subject_url")
    private String subjectUrl;

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
