package com.example.classmarangethouduan.controller;

import com.example.classmarangethouduan.pojo.Result;
import com.example.classmarangethouduan.pojo.User;
import com.example.classmarangethouduan.service.LoginService;
import com.example.classmarangethouduan.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @UserName 程序员_Suxiaoxiang
 * @date 2024/8/13 15:50
 * @Version 1.0
 */
@Slf4j
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 用户登录
     * @Username 程序员-Su_xiaoxiang
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        log.info("用户登录信息为: {}", user);

        // 验证用户
        User userLogin = loginService.login(user);
        User selectUserByUsername = loginService.getUsername(user);

        if (selectUserByUsername == null) {
            return Result.error("用户不存在,请前往注册");
        } else if (userLogin != null) {
            String userId = userLogin.getStuId();
            String existingJwt = jwtUtils.getJwtFromCache(userId); // 从缓存中获取 JWT

            if (existingJwt != null && jwtUtils.isJwtValid(existingJwt)) {
                // 如果缓存中有有效的 JWT 令牌，则返回该令牌
                log.info("使用现有的JWT令牌");
                Map<String, Object> userLoginInfo = new HashMap<>();
                userLoginInfo.put("userId", userId);
                userLoginInfo.put("token", existingJwt.trim()); // 确保返回的数据没有多余空白
                return Result.success(userLoginInfo);
            } else {
                // 生成新的 JWT 令牌
                Map<String, Object> claims = new HashMap<>();
                claims.put("id", userLogin.getStuId());
                claims.put("username", userLogin.getStuUserName());
                String newJwt = JwtUtils.generateJwt(claims);
                newJwt = newJwt.trim(); // 去除前后空白字符
                jwtUtils.storeJwtInCache(userId, newJwt); // 将新的 JWT 存储到缓存中
                log.info("生成的JWT令牌为: {}", newJwt);
                log.info("用户登录成功");
                Map<String, Object> userLoginInfo = new HashMap<>();
                userLoginInfo.put("userId", userId);
                userLoginInfo.put("token", newJwt);
                return Result.success(userLoginInfo);
            }
        } else {
            return Result.error("用户名或密码错误");
        }
    }

}
