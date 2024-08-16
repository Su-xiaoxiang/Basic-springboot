package com.example.classmarangethouduan.mapper;

import com.example.classmarangethouduan.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @UserName 程序员_Suxiaoxiang
 * @date 2024/8/13 15:52
 * @Version 1.0
 */
@Mapper
public interface LoginMapper {
    /**
     * 登录
     * @param user
     * @Username 程序员-Su_xiaoxiang
     * @date 2024/8/16 22:52
     * @return
     */
    @Select("select * from user where stuUserName=#{username} and stuPassWord=#{password}")
    User login(User user);

    /**
     * 查询用户名是否存在
     * @param user
     * @Username 程序员-Su_xiaoxiang
     * @date 2024/8/16 22:57
     * @return
     */
    @Select("select * from user where stuUserName=#{username}")
    User SelectUserUsername(User user);
}
