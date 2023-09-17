package xhu.click.wx.login.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xhu.click.common.entity.constants.RedisConstants;
import xhu.click.common.entity.pojo.Result;
import xhu.click.common.utils.JwtUtil;
import xhu.click.db.entity.dto.UserDto;
import xhu.click.db.entity.pojo.User;
import xhu.click.wx.login.entity.pojo.WxResponse;
import xhu.click.wx.login.service.WxLoginService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static xhu.click.common.entity.constants.RedisConstants.LOGIN_USER_KEY;
import static xhu.click.common.entity.constants.RedisConstants.LOGIN_USER_TTL;

@Api(tags = "微信登录")
@RestController
@RequestMapping("/wx")
public class WxLoginController {

    @Autowired
    WxLoginService wxLoginService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @ApiOperation("通过微信code获取token")
    @ApiImplicitParam(name = "code",value = "login码",required = true)
    @PostMapping("login")
    public Result getCode(String code) {
        WxResponse wxResponse = wxLoginService.getWxResponse(code);
        User user = new User();
        /**
         * todo 登录
         */
        return Result.ok();
//        查询user
//        不存在则构建user并保存
//        存在user
//        UserDto userDto = BeanUtil.copyProperties(user, UserDto.class);
//        String token=JwtUtil.setJwt(userDto, DateUtil.offsetDay(new Date(), (int)RedisConstants.LOGIN_USER_TTL));
//        String key = LOGIN_USER_KEY + token;
//        Map<String, Object> userMap = BeanUtil.beanToMap(userDto, new HashMap<>(), CopyOptions.create()
//                .setIgnoreNullValue(true)
//                .setFieldValueEditor((fieldName, fieldValue) -> {
//                    return fieldValue.toString();
//                }));
//        stringRedisTemplate.opsForHash().putAll(key,userMap);
//        stringRedisTemplate.expire(token,LOGIN_USER_TTL, TimeUnit.DAYS);
//        return Result.ok(token);
    }

}
