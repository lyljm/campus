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
 * 
 * </p>
 *
 * @author eifying
 * @since 2023-09-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("campus_interest_user")
@ApiModel(value="InterestUser对象", description="关注用户表")
public class InterestUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "user_id")
    private String userId;

    @ApiModelProperty(value = "关注的用户")
    @TableField("interest_user_id")
    private String interestUserId;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "add_time",fill = FieldFill.INSERT)
    private LocalDateTime addTime;


}
