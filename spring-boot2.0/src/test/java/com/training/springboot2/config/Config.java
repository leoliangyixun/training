package com.training.springboot2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.GenericWebApplicationContext;

import javax.annotation.PostConstruct;

/**
 * @author yangkai
 * @date 2018/5/1
 * @email yangkai@hujiang.com
 * @description
 */
public class Config {
    @Autowired
    private GenericWebApplicationContext context;

    @PostConstruct
    public void initConfig() {
       // context.registerBean();
    }
}
