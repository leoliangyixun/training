package com.training.spring.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author yangkai
 * @date 2021/10/21
 * @email yangkai@hujiang.com
 * @description
 */
@SpringBootApplication
@EnableCaching  //开启缓存
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
