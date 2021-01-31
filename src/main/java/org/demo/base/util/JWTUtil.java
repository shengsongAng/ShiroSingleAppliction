package org.demo.base.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class JWTUtil {

    // 过期时间5分钟
    private static final long EXPIRE_TIME = 5*60*1000;

    //秘钥
    private static final String SIGN_KEY = "!WA$5H%H^5YH*3UY4%JG!S89UE#";

    /**
     * 校验token是否正确
     * @param token 密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,5min后过期
     * @param username 用户名
     * @param secret 用户的密码
     * @return 加密的token
     */
    public static String sign(String username, String secret) {
        try {
            Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withClaim("username", username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 生成签名
     */
    public static String getToken(Map<String,String> map){
        try{
            //生成builder
            final JWTCreator.Builder builder = JWT.create();

            //payload
            map.forEach((k,v)->{
                builder.withClaim(k,v);
            });

            //设置过期时间，1H
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR,1);

            String token = builder.withExpiresAt(calendar.getTime())//过期时间
                    .sign(Algorithm.HMAC256(SIGN_KEY));

            return token;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 验证token，并返回
     */
    public DecodedJWT validateToken(String token) throws Exception{
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN_KEY)).build().verify(token);
        return verify;
    }
}
