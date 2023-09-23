package xhu.click.db.entity.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
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
@TableName("campus_postliked")
@ApiModel(value="Postliked对象", description="")
@NoArgsConstructor
@AllArgsConstructor
public class Postliked implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "postId")
    @TableId(value = "post_id")
    private Long postId;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "add_time",fill = FieldFill.INSERT)
    private LocalDateTime addTime;


}
