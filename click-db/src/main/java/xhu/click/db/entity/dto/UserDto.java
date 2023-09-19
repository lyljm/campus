package xhu.click.db.entity.dto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UserDTO对象", description = "用户表")
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户表ID")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty(value = "邀请者")
    @TableField("inviter")
    private String inviter;

    @ApiModelProperty(value = "用户名称")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "性别：0 未知， 1男， 2女")
    @TableField("gender")
    private Integer gender;

    @ApiModelProperty(value = "个性标签")
    @TableField("label")
    private String label;

    @ApiModelProperty(value = "背景图片")
    @TableField("back_url")
    private String backUrl;

    @ApiModelProperty(value = "最近一次登录时间")
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "最近一次登录IP地址")
    @TableField("last_login_ip")
    private String lastLoginIp;

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

    @ApiModelProperty(value = "0 可用, 1 禁用, 2 注销")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField("add_time")
    private LocalDateTime addTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

}
