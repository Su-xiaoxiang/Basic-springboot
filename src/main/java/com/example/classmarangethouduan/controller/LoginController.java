package com.example.classmarangethouduan.controller;

import com.example.classmarangethouduan.pojo.Result;
import com.example.classmarangethouduan.pojo.User;
import com.example.classmarangethouduan.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/login")
    public Result login(@RequestBody User user){
      log.info("用户登录信息为: "+user);
      loginService.login(user);
      return Result.error("登录失败");
    }
}
