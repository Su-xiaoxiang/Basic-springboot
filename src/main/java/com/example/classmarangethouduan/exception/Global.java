package com.example.classmarangethouduan.exception;


import com.example.classmarangethouduan.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
* 全局异常处理器
*
* */
@Slf4j
@RestControllerAdvice
public class Global {
    @ExceptionHandler(Exception.class)
    public Result ex(Exception Bigex){
        log.info(String.valueOf(Bigex));
         return Result.error("对不起，操作失败请联系管理员");
    }
}
