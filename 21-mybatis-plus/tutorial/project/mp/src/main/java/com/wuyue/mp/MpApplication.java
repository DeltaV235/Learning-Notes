package com.wuyue.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author DeltaV235
 * @date 2020/8/2 17:57
 * @description SpringBoot启动类
 */
@SpringBootApplication
@MapperScan("com.wuyue.**.mapper")
public class MpApplication {

    public static void main(String[] args) {
        SpringApplication.run(MpApplication.class, args);
    }

}
