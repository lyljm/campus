package xhu.click.server.controller;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xhu.click.common.entity.pojo.ResultVO;
import xhu.click.common.utils.thread.LocalHolder;
import xhu.click.db.entity.dto.UserDto;
import xhu.click.db.entity.pojo.User;
import xhu.click.db.service.IUserService;


@Slf4j
@Api(tags = "用户相关")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @ApiOperation("通过openid获取用户详细信息")
    @GetMapping("{openid}")
    public ResultVO getUserDetails(@PathVariable String openid) {
        User user = new User();
        user.setOpenid(openid);
        user = userService.getByOpenid(user);
        UserDto userDto = BeanUtil.copyProperties(user, UserDto.class);
        return ResultVO.ok(userDto);
    }

    @ApiOperation("修改用户")
    @PutMapping
    public ResultVO modifyUser(@RequestBody UserDto userDto) {
/**
 * todo 修改用户
 */
        return null;
    }

    @ApiOperation("注销用户")
    @DeleteMapping
    public ResultVO deleteUser() {
        UserDto user = (UserDto) LocalHolder.getObject();
        userService.deleteByOpenid(user.getOpenid());
        log.info("删除用户：{} 成功", user.getOpenid());
        return ResultVO.ok();
    }

}
