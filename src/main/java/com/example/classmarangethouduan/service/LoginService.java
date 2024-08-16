package com.example.classmarangethouduan.service;

import com.example.classmarangethouduan.pojo.User;

/**
 * @UserName 程序员_Suxiaoxiang
 * @date 2024/8/13 15:51
 * @Version 1.0
 */
public interface LoginService {
    /**
     * 登录操作
     *
     * @param user
     * @return
     * @Username 程序员-Su_xiaoxiang
     */
    User login(User user);

    /**
     * 查询用户名是否存在
     * @param user
     * @Username 程序员-Su_xiaoxiang
     * @date 2024/8/16 22:56
     * @return
     */
    User getUsername(User user);
}
