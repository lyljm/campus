package xhu.click.server.intercepter;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import xhu.click.common.entity.constants.RedisConstants;
import xhu.click.common.utils.JwtUtil;
import xhu.click.common.utils.thread.LocalHolder;
import xhu.click.db.entity.dto.UserDto;
import xhu.click.wx.login.entity.pojo.Payload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;


/**
 * 刷新token的拦截器
 */
@Slf4j
public class RefreshTokenInterceptor implements HandlerInterceptor {


    private StringRedisTemplate stringRedisTemplate;

    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取请求头中的token
        String token = request.getHeader("authorization");
        if (StrUtil.isBlank(token)) {
            return true;
        }
        //获取负载，转换为Payload
        Object verify = JwtUtil.getPayload(token);
        String payloadStr = Base64Decoder.decodeStr((CharSequence) verify);
        Payload payload = JSONUtil.toBean(payloadStr, Payload.class);
        // 基于openid获取redis中的用户
        String key = RedisConstants.LOGIN_USER_KEY + payload.getUser();
        String userDto = stringRedisTemplate.opsForValue().get(key);
        if(userDto==null){
            return true;
        }
        // 将查询到的json数据转为UserDTO
        UserDto userDTO = JSONUtil.toBean(userDto, UserDto.class);
        // 存在，保存用户信息到 ThreadLocal
        LocalHolder.saveObject(userDTO);
        // 刷新token有效期
        stringRedisTemplate.expire(key, RedisConstants.LOGIN_USER_TTL, TimeUnit.DAYS);
        // 放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 移除用户
        LocalHolder.removeObject();
    }
}
