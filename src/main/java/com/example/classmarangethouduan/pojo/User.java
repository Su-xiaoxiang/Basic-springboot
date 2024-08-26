package com.example.classmarangethouduan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String stuId;// 学号
    private String stuUserName; //用户名
    private String password; //密码
    private String  Phone;//电话
    private String CLassName;//班级
}
