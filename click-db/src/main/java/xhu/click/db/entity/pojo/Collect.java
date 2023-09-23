package xhu.click.db.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author eifying
 * @since 2023-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("campus_collect")
@ApiModel(value="Collect对象", description="图文收集对象")
@NoArgsConstructor
@AllArgsConstructor
public class Collect implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "user_id")
    private String userId;

    @ApiModelProperty(value = "图文id")
    @TableField("post_id")
    private Long postId;

    @ApiModelProperty(value = "添加时间")
    @TableField("add_time")
    private LocalDateTime addTime;


}
