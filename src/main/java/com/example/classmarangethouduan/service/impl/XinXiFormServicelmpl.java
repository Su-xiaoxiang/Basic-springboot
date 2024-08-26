package com.example.classmarangethouduan.service.impl;

import com.example.classmarangethouduan.mapper.XinXiFormMapper;
import com.example.classmarangethouduan.pojo.User;
import com.example.classmarangethouduan.service.XinXiFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @UserName 程序员_Suxiaoxiang
 * @date 2024/8/24 22:04
 * @Version 1.0
 */
@Service
public class XinXiFormServicelmpl implements XinXiFormService {
    @Autowired
    private XinXiFormMapper xinXiFormMapper;
    /**
     * 获取用户信息
     *
     * @param id
     * @Username 程序员-Su_xiaoxiang
     * @date 2024/8/24 22:10
     */
    @Override
    public Map<String, Object> getXinXiForm(Long id) {
       User user=xinXiFormMapper.getXinXiForm(id);
       Map<String,Object> userInfo =new HashMap<>();
       userInfo.put("stuId",user.getStuId());
       userInfo.put("username",user.getStuUserName());
       userInfo.put("Phone",user.getPhone());
       userInfo.put("ClassName",user.getCLassName());
       return userInfo;
    }
}
