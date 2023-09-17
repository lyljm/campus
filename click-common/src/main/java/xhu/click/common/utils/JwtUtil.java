package xhu.click.common.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;

public class JwtUtil {
    public static final String secret = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJpc3MiOiJhdXRoMCJ9.AbIJTDMFc7yUa5MhvcP03nJPyCPzZtQcGEp-zWfOkEE";

    /**
     * 生成jwt
     * @param object
     * @return
     */
    public static final String setJwt(Object object,Date expireTime) {
        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            token = JWT.create()
                    .withIssuer("click")
                    .withClaim("user", JSONUtil.toJsonStr(object))
                    .withIssuedAt(new Date())   // 生成签名的时间
                    .withExpiresAt(expireTime)
//                    .withExpiresAt(DateUtil.offsetMonth(new Date(),1))   // 生成签名的有效期,月
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            /**
             * todo
             * 全局异常处理
             */
            throw exception;
            // Invalid Signing configuration / Couldn't convert Claims.
        }
        return token;
    }

    /**
     * 验证jwt
     * @param token
     * @return
     */
    public static final Object verify(String token){
        DecodedJWT decodedJWT = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    // specify an specific claim validations
                    .withIssuer("click")
                    // reusable verifier instance
                    .build();
            decodedJWT = verifier.verify(token);
        } catch (JWTVerificationException exception){
            return false;
        }
        return true;
    }

    /**
     * 获取payLoad
     * @param token
     * @return
     */
    public static final Object getPayload(String token){
        DecodedJWT decodedJWT = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    // specify an specific claim validations
                    .withIssuer("click")
                    // reusable verifier instance
                    .build();

            decodedJWT = verifier.verify(token);
        } catch (JWTVerificationException exception){
            return null;
        }
        return decodedJWT.getPayload();
    }
}
