package com.training.springboot.config;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;

import com.alibaba.druid.pool.DruidDataSource;

//@Configuration
//@PropertySource({"classpath:application.properties"})
public class MySQLDataSourceConfig {
    
    @Bean
    public DataSource druidDataSource(@Value("#{pros['jdbc.driverClassName']}") String driver,
                                      @Value("#{pros['jdbc.url']}") String url,
                                      @Value("#{pros['jdbc.username']}") String username,
                                      @Value("#{pros['jdbc.password']}") String password) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        try {
            druidDataSource.setFilters("stat, wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return druidDataSource;
    }
    
    @Bean
    PropertiesFactoryBean pros(){
        PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
        factoryBean.setLocation(new ClassPathResource("application.properties"));
        factoryBean.setFileEncoding(StandardCharsets.UTF_8.name());
        return factoryBean;
    }
    
}


