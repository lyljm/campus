package xhu.click.wx.login.service;


import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.log4j.Log4j2;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import xhu.click.common.utils.JwtUtil;
import xhu.click.db.entity.dto.UserDto;
import xhu.click.wx.login.entity.constrants.WxConstrants;
import xhu.click.wx.login.entity.pojo.WxResponse;

import java.io.IOException;
import java.util.Date;

@Log4j2
@Service
public class WxLoginService {

    /**
     * 通过code获取WxResponse
     * @param code
     * @return WxResponse
     */
    public WxResponse getWxResponse(String code) {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder queryUrlBuilder = HttpUrl.get("https://api.weixin.qq.com/sns/jscode2session").newBuilder();
        queryUrlBuilder.addQueryParameter("appid", WxConstrants.APPID);
        queryUrlBuilder.addQueryParameter("secret", WxConstrants.SECRET);
        queryUrlBuilder.addQueryParameter("js_code", WxConstrants.JSCODE);
        queryUrlBuilder.addQueryParameter("grant_type", WxConstrants.GRANT_TYPE);
        Request request = new Request.Builder()
                .url(queryUrlBuilder.build())
                .build();
        String responseStr = null;
        WxResponse wxResponse = null;
        try {
//            发送请求
            Response response = client.newCall(request).execute();
            responseStr = response.toString();
            wxResponse = JSONObject.parseObject(response.body().string(), WxResponse.class);
            log.info("微信获取openid成功",wxResponse.toString());
        } catch (IOException e) {
            log.error("微信登录失败：code" + code + " responseStr" + responseStr);
            e.printStackTrace();
        }
        return wxResponse;
    }

    /**
     * 产生UserDto的JWT
     * @param userDto
     * @return
     */
    private String generateJWT(UserDto userDto){
        return JwtUtil.setJwt(userDto, DateUtil.offsetDay(new Date(),WxConstrants.EXPIRE_DAY));
    }
}
