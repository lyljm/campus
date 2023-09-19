package xhu.click.common.utils;


import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.date.DateUtil;
import org.junit.Test;

import java.util.Date;



public class JWTTest {
    @Test
    public void testGenerator() throws InterruptedException {
       String ttl="ssfs";
        String s = JwtUtil.setJwt(ttl, DateUtil.offsetMonth(new Date(),1));
        System.out.println(s);
//        Thread.sleep(1000);
        Object verify = JwtUtil.getPayload(s);
        String s1 = Base64Decoder.decodeStr((CharSequence) verify);
        System.out.println(s1);
    }
}
