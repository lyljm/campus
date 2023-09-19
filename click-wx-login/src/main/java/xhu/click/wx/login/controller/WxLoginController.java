package xhu.click.wx.login.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xhu.click.common.entity.constants.RedisConstants;
import xhu.click.common.entity.enums.ResultCode;
import xhu.click.common.entity.pojo.ResultVO;
import xhu.click.common.exception.BusinessException;
import xhu.click.common.utils.JwtUtil;
import xhu.click.common.utils.RedisIdWorker;
import xhu.click.db.entity.dto.UserDto;
import xhu.click.db.entity.pojo.User;
import xhu.click.db.service.IUserService;
import xhu.click.wx.login.entity.constrants.Constrants;
import xhu.click.wx.login.entity.pojo.WxResponse;
import xhu.click.wx.login.service.WxLoginService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Slf4j
@Api(tags = "微信登录")
@RestController
@RequestMapping("/wx")
public class WxLoginController {

    @Autowired
    WxLoginService wxLoginService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    IUserService userService;

    @Autowired
    RedisIdWorker redisIdWorker;

    @ApiOperation("通过微信code获取token")
    @ApiImplicitParam(name = "code",value = "login码",required = true)
    @PostMapping("login")
    public ResultVO getCode(@NotNull String code) {
        if(code==null){
            throw new BusinessException(ResultCode.ERROR);
        }
        WxResponse wxResponse = wxLoginService.getWxResponse(code);
        wxResponse.setOpenid(code);
        /**
         * todo 登录
         */
        User user = new User();
        user.setOpenid(wxResponse.getOpenid());
//        查询user
        user = userService.getByOpenid(user);
        if(user==null){
            //        不存在则构建user并保存
            log.info("openid",wxResponse.getOpenid());
            user=new User();
            user.setId(String.valueOf(redisIdWorker.nextId(RedisConstants.NEX_ID_PREFIX)));
            user.setGender(0);
            user.setOpenid(wxResponse.getOpenid());
            user.setLastLoginTime(LocalDateTime.now());
            user.setNickName("user_"+ RandomUtil.randomString(10));
            user.setAvatarUrl("");
            user.setLabel(Constrants.DEFAULT_LABEL);
            userService.save(user);
        }
//        存在user
        UserDto userDto=new UserDto();
        BeanUtil.copyProperties(user,userDto);
        log.info("用户："+userDto.getOpenid()+" 登录,{}",userDto.toString());
        String token=JwtUtil.setJwt(userDto.getOpenid(), new Date("2099/12/30"));
        String openid=userDto.getOpenid();
        String key = RedisConstants.LOGIN_USER_KEY + openid;
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(userDto), RedisConstants.LOGIN_USER_TTL,TimeUnit.DAYS);
        return ResultVO.ok(token);
    }

}
