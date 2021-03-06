package com.dong.shop.global.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.dong.shop.domain.entity.SysUser;
import com.dong.shop.global.constant.Constant;
import com.dong.shop.global.util.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author caishaodong
 * @Date 2020-09-18 16:21
 * @Description
 **/
public class JwtUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);
    /**
     * token请求头
     */
    public static final String TOKEN_HEADER = "Authorization";
    /**
     * 密钥
     */
    private static final String SECRET = "my_secret";

    /**
     * 过期时间（单位：秒）
     **/
    private static final long EXPIRATION = 7 * 24 * 60 * 60L;

    /**
     * 生成token
     *
     * @param sysUser
     * @return
     */
    public static String createToken(SysUser sysUser) {
        //过期时间
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
        Map<String, Object> map = new HashMap<>();
        // 加密算法
        map.put("alg", "HS256");
        // token类型
        map.put("typ", "JWT");
        String token = JWT.create()
                // 添加头部
                .withHeader(map)
                // 可以将基本信息放到claims中
                .withClaim(Constant.USER_ID, sysUser.getId())
                .withClaim(Constant.USER_NAME, sysUser.getUsername())
                // 超时设置,设置过期的日期
                .withExpiresAt(expireDate)
                // 签发时间
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (TokenExpiredException e) {
            LOGGER.error("token已失效", e);
            return null;
        } catch (Exception e) {
            LOGGER.error("token解析异常", e);
            return null;
        }
        return jwt.getClaims();
    }

    /**
     * 根据token获取UserId
     *
     * @param token
     * @return
     */
    public static Long getUserIdByToken(String token) {
        if (StringUtil.isBlank(token)) {
            return null;
        }
        Map<String, Claim> claimMap = verifyToken(token);
        Long userId = Objects.isNull(claimMap) ? null : claimMap.get(Constant.USER_ID).asLong();
        return userId;
    }

    /**
     * 根据token获取用户信息
     *
     * @param token
     * @return
     */
    public static SysUser getSysUserByToken(String token) {
        if (StringUtil.isBlank(token)) {
            return null;
        }
        Map<String, Claim> claimMap = verifyToken(token);
        return getUserByClaimMap(claimMap);
    }

    /**
     * 根据claimMap获取用户信息
     *
     * @param claimMap
     * @return
     */
    public static SysUser getUserByClaimMap(Map<String, Claim> claimMap) {
        if (Objects.isNull(claimMap)) {
            return null;
        }
        SysUser sysUser = new SysUser();
        for (String key : claimMap.keySet()) {
            Claim claim = claimMap.get(key);
            switch (key) {
                case Constant.USER_ID:
                    sysUser.setId(claim.asLong());
                    break;
                case Constant.USER_NAME:
                    sysUser.setUsername(claim.asString());
                    break;
                case "exp":
                case "iat":
                    System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").format(claim.asDate()));
                    break;
            }
        }

        return sysUser;
    }

    public static void main(String[] args) {
        SysUser sysUser = new SysUser();
        sysUser.setId(1L);
        sysUser.setUsername("");
        String token = createToken(sysUser);
        token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiIiwidXNlck5hbWUiOiLlvKDkuIkiLCJleHAiOjE2MDA0MjAzNjgsInVzZXJJZCI6MSwiaWF0IjoxNjAwNDIwMzYzfQ.nkXZllfe75EyRemmKVYFLdX4eRTlqp-2r-Q2IV9oHjE";
        System.out.println(token);
        System.out.println(getUserIdByToken(token));
        System.out.println(getSysUserByToken(token));

        Map<String, Claim> claimMap = verifyToken(token);
        System.out.println(getUserByClaimMap(claimMap));

    }
}
