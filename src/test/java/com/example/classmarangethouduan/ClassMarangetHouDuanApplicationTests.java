package com.example.classmarangethouduan;

import com.example.classmarangethouduan.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ClassMarangetHouDuanApplicationTests {
    @Autowired
       private RedisTemplate<String, String> redisTemplate;
    @Test
    public void main() {
        String key = "testKey2";
        String value = "testValue";

        redisTemplate.opsForValue().set(key, value);
        String result = redisTemplate.opsForValue().get(key);
        System.out.println("Stored value: " + result);
    }
}

