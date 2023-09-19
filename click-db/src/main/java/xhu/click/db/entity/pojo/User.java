package xhu.click.db.entity.pojo;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author eifying
 * @since 2023-09-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("campus_user")
@ApiModel(value="User对象", description="用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户表ID")
    @TableId(value = "id")
    private String id;

    @ApiModelProperty(value = "邀请者")
    @TableField("inviter")
    private String inviter;

    @ApiModelProperty(value = "用户名称")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "用户密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "真实姓名")
    @TableField("true_name")
    private String trueName;

    @ApiModelProperty(value = "性别：0 未知， 1男， 2女")
    @TableField("gender")
    private Integer gender;

    @ApiModelProperty(value = "个性标签")
    @TableField("label")
    private String label;

    @ApiModelProperty(value = "背景图片")
    @TableField("back_url")
    private String backUrl;

    @ApiModelProperty(value = "生日")
    @TableField("birthday")
    private LocalDateTime birthday;

    @ApiModelProperty(value = "个人分享图片")
    @TableField("share_url")
    private String shareUrl;

    @ApiModelProperty(value = "最近一次登录时间")
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "最近一次登录IP地址")
    @TableField("last_login_ip")
    private String lastLoginIp;

    @ApiModelProperty(value = "0 普通用户，1 VIP用户，2 高级VIP用户")
    @TableField("user_level")
    private Integer userLevel;

    @ApiModelProperty(value = "用户等级")
    @TableField("integral")
    private BigDecimal integral;

    @ApiModelProperty(value = "用户昵称或网络名称")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty(value = "用户手机号码")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty(value = "用户头像图片")
    @TableField("avatar_url")
    private String avatarUrl;

    @ApiModelProperty(value = "微信登录openid")
    @TableField("openid")
    private String openid;

    @ApiModelProperty(value = "微信登录会话KEY")
    @TableField("session_key")
    private String sessionKey;

    @ApiModelProperty(value = "0 可用, 1 禁用, 2 注销")
    @TableField("status")
    private Integer status;

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

    @ApiModelProperty(value = "租户ID，用于分割多个租户")
    @TableField("tenant_id")
    private String tenantId;

    @ApiModelProperty(value = "更新版本号")
    @TableField("version")
    @Version
    private Integer version;


}
