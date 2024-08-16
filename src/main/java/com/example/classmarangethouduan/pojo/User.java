package com.example.classmarangethouduan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer stuId;// 学号
    private String username; //用户名
    private String password; //密码
}
