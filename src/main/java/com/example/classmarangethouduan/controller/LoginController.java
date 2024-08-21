package com.example.classmarangethouduan.controller;

import com.example.classmarangethouduan.pojo.Result;
import com.example.classmarangethouduan.pojo.User;
import com.example.classmarangethouduan.service.LoginService;
import com.example.classmarangethouduan.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 用户登录
     * @param user
     * @Username 程序员-Su_xiaoxiang
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user){
      log.info("用户登录信息为: {}", user);
      User userLogin=loginService.login(user);
      User SelectUserUsername=loginService.getUsername(user);
      if (SelectUserUsername == null){
          return Result.error("用户不存在,请前往注册");
      } else if (userLogin != null) {
          Map<String, Object> claims = new HashMap<>();
          claims.put("id", userLogin.getStuId());
          claims.put("username", userLogin.getUsername());
          claims.put("password", userLogin.getPassword());
          String jwt = JwtUtils.generateJwt(claims);
          return Result.success(jwt);
      }else {
          return Result.error("用户名或密码错误");
      }
    }
}
