package com.example.classmarangethouduan.service.impl;

import com.example.classmarangethouduan.mapper.LoginMapper;
import com.example.classmarangethouduan.pojo.User;
import com.example.classmarangethouduan.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @UserName 程序员_Suxiaoxiang
 * @date 2024/8/13 15:52
 * @Version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    /**
     * @param user
     * @return
     * @Username 程序员-Su_xiaoxiang
     * @date 2024/8/16 22:48
     */
    @Override
    public User login(User user) {
        return loginMapper.login(user);
    }

    /**
     * 查询用户名是否存在
     * @param user
     * @Username 程序员-Su_xiaoxiang
     * @date 2024/8/16 22:56
     * @return
     */
    @Override
    public User getUsername(User user) {
        return loginMapper.SelectUserUsername(user);
    }
}
