package com.example.classmarangethouduan.controller.user;

import com.example.classmarangethouduan.pojo.Result;
import com.example.classmarangethouduan.pojo.User;
import com.example.classmarangethouduan.service.XinXiFormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @UserName 程序员_Suxiaoxiang
 * @date 2024/8/24 21:56
 * @Version 1.0
 */

@RestController
@Slf4j
public class XinXiFormController {
    @Autowired
    private XinXiFormService xinXiFormService;
    /**
     *
     * 获取用户信息
     * @Username 程序员-Su_xiaoxiang
     * @date 2024/8/24 22:01
     *
     */
    @GetMapping("/getUserInfo")
    public Result getXinXiForm(@RequestParam(value = "userId") Long userId){
        log.info("-----------------------------控制层信息展示------------------------------");
        Map<String, Object> user=xinXiFormService.getXinXiForm(userId);
        log.info("用户的学号:"+userId);
        log.info("-----------------------------控制层信息展示完毕------------------------------");
        return Result.success(user);
    }
}
