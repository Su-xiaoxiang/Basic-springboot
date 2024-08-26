package com.example.classmarangethouduan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@Slf4j
public class ClassMarangetHouDuanApplication {

    public static void main(String[] args) {
        log.info("服务器已启动");
        SpringApplication.run(ClassMarangetHouDuanApplication.class, args);
    }

}
