package cn.edu.school.schoolcommon.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

/**
 * Jwt工具类，用于生成和解析jwt
 */
public class JwtUtil {

    /**
     * 加密密钥
     */
    private static final String KEY = "SchoolSystem";

    /**
     * 接收业务数据载荷,生成token并返回
     * @param claims 数据载荷
     * @return token
     */
    public static String genToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
                .sign(Algorithm.HMAC256(KEY));
    }

    /**
     * 接收token,验证token,并返回业务数据
     * @param token 当前token
     * @return 解析出来的数据载荷
     */
    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }

}
