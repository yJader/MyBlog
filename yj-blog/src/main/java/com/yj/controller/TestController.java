package com.yj.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Package com.yj.controller
 * @Author yJade
 * @Date 2023-02-16 11:15
 */

@RestController
@Data
@PropertySource("classpath:key.properties")
@ConfigurationProperties(prefix = "oss")
public class TestController {
    private String abc;

    private String accessKey;
    private String secretKey;
    private String bucket;
    private String url;

    public void setAbc(String abc) {
        this.abc = abc;
    }

    @GetMapping("/test")
    public void test() {
        System.out.println(accessKey);
        System.out.println(secretKey);
        System.out.println(bucket);
        System.out.println(url);
    }


}
