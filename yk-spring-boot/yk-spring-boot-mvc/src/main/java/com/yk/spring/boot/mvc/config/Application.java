/**
 * 
 */
/**
 * @author yangkai
 *
 */
package com.yk.spring.boot.mvc.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.yk.spring.boot.mvc"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}