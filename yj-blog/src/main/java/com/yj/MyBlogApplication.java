package com.yj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author yJade
 * @Date 2023-02-09 18:02
 * @Package com.yj
 * @Description: 
 */
@MapperScan("com.yj.mapper")
@SpringBootApplication
@EnableScheduling
public class MyBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyBlogApplication.class, args);
    }
}
