package com.example.classmarangethouduan.mapper;

import com.example.classmarangethouduan.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @UserName 程序员_Suxiaoxiang
 * @date 2024/8/24 22:05
 * @Version 1.0
 */
@Mapper
public interface XinXiFormMapper {
    /**
     *
     * @param id
     * @Username 程序员-Su_xiaoxiang
     * @date 2024/8/24 22:11
     */
    @Select("select u.stuId ,u.stuUserName, u.Phone ,stc.ClassName from user u,stuclass stc where u.stuId=#{id} and stc.stuId=#{id} and u.stuId=stc.stuId;")
    User getXinXiForm(Long id);
}
