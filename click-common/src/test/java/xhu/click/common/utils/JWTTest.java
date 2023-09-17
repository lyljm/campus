package xhu.click.common.utils;


import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.date.DateUtil;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JWTTest {
    @Test
    public void testGenerator() throws InterruptedException {
        Map<String, Object>map=new HashMap<>();
        map.put("sss","sssss");
        String s = JwtUtil.setJwt(map, DateUtil.offsetMonth(new Date(),1));
        System.out.println(s);
//        Thread.sleep(1000);
        Object verify = JwtUtil.getPayload(s);
        String s1 = Base64Decoder.decodeStr((CharSequence) verify);
        System.out.println(s1);
    }
}
