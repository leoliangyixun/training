package com.yk.spring.boot.mvc.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableAutoConfiguration
@EnableWebMvc
public class Config extends WebMvcConfigurerAdapter {
    
   @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
       viewResolver.setViewClass(JstlView.class);
       viewResolver.setPrefix("/WEB-INF/views/");
       viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

}
