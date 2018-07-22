package com.kerwinyang.training.apns.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"com.kerwinyang.training.apns.api.controller"})
@PropertySource({"classpath:application-testcase.properties"})
public class ControllerConfig {
}
