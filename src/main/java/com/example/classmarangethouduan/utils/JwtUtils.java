package com.example.classmarangethouduan.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class JwtUtils {

    private final static String signKey = "Class-target"; // 定义主要的标识
    private static final Long expireDate = 3600000L; // 定义有效期时间为4个小时 毫秒
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 生成JWT令牌
     * @param claims JWT第二部分负载 payload 中存储的内容
     */
    public static String generateJwt(Map<String, Object> claims){
        return Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setExpiration(new Date(System.currentTimeMillis() + expireDate))
                .compact();
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt){
        return Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    /**
     *
     * @param userId
     * @Username 程序员-Su_xiaoxiang
     * @date 2024/8/26 10:34
     */
    public String getJwtFromCache(String userId) {
        log.info("--------------开始进行判断Redis是否含有token--------------");
        log.info("Redis得到数据的Id:{}", userId);
        String res = redisTemplate.opsForValue().get(userId);
        log.info("Redis得到的数据为:{}", res);
        log.info("------------判断结束--------------");
        return res;
    }

    public void storeJwtInCache(String userId, String jwt) {
        try {
            log.info("--------------进行Redis存储--------------");
            log.info("开始存储JWT，用户ID: {}", userId);
            String cleanToken = jwt.trim();
            redisTemplate.opsForValue().set(userId, cleanToken,1, TimeUnit.HOURS);
            log.info("JWT存储成功，用户ID: {}, 数据为: {}", userId, cleanToken);
        } catch (Exception e) {
            log.error("存储JWT时出现异常，用户ID: {}", userId, e);
        }
    }


    public boolean isJwtValid(String jwt) {
        try {
            Claims claims = parseJWT(jwt);
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
