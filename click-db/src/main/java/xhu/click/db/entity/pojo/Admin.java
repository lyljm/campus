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
 * 管理员表
 * </p>
 *
 * @author eifying
 * @since 2023-09-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("campus_admin")
@ApiModel(value="Admin对象", description="管理员表")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "管理员表ID")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty(value = "管理员名称")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "管理员密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "最近一次登录IP地址")
    @TableField("last_login_ip")
    private String lastLoginIp;

    @ApiModelProperty(value = "最近一次登录时间")
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "微信openid")
    @TableField("openid")
    private String openid;

    @ApiModelProperty(value = "用户手机号码")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty(value = "管理员邮箱")
    @TableField("mail")
    private String mail;

    @ApiModelProperty(value = "性别：0 未知， 1男， 2 女")
    @TableField("gender")
    private Integer gender;

    @ApiModelProperty(value = "头像图片")
    @TableField("avatar")
    private String avatar;

    @TableField(value = "add_time",fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime addTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除")
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;

    @ApiModelProperty(value = "角色列表")
    @TableField("role_ids")
    private String roleIds;

    @ApiModelProperty(value = "租户ID，用于分割多个租户")
    @TableField("tenant_id")
    private String tenantId;

    @ApiModelProperty(value = "更新版本号")
    @TableField("version")
    @Version
    private Integer version;


}
