package org.learn.mysecurity.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * jwt工具类 jws实现
 * Created by wqg on 2023/1/5.
 */
public class JwtUtil {

    // jwt有效期
    private static final Long JWT_TTL = 60 * 60 * 1000L;

    // 设置密钥明文
    private static final String JWT_KEY = "wqgKey";

    // 密钥明文加密算法
    private static final String JWT_KEY_ENCRYPT_ALGORITHM = "AES";

    // jwt签发者
    private static final String JWT_ISSUER = "wqg";

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成jwt/jws
     *
     * @param subject token中要存放的数据（json格式）
     * @return
     */
    public static String createJwt(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());
        return builder.compact();
    }

    /**
     * 生成jwt/jws
     *
     * @param subject   token中要存放的数据（json格式）
     * @param ttlMillis 过期时间
     * @return
     */
    public static String createJwt(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());
        return builder.compact();
    }

    public static String createJwt(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);
        return builder.compact();
    }

    /**
     * 解析jwt/jws
     *
     * @param jwt
     * @return
     */
    public static Claims parseJwt(String jwt) {
        SecretKey secretKey = generateSecretKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generateSecretKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JWT_TTL;
        }
        long expireMillis = nowMillis + ttlMillis;
        Date expireDate = new Date(expireMillis);

        return Jwts.builder()
                .setId(uuid) // 唯一id
                .setSubject(subject) // 主题 可以是json数据
                .setIssuer(JWT_ISSUER)  // 签发者
                .setIssuedAt(now)  // 签发时间
                .signWith(signatureAlgorithm, secretKey) // 使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expireDate);
    }

    /**
     *  生成密钥
     * @return
     */
    private static SecretKey generateSecretKey() {
        byte[] encodedKey = Base64.getEncoder().encode(JWT_KEY.getBytes());
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, JWT_KEY_ENCRYPT_ALGORITHM);
    }

}
