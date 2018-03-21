
package com.training.springboot.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.druid.pool.DruidDataSource;

//@Configuration
//@PropertySource({"classpath:application.properties"})
public class MySQLDataSourceConfig2 {

    
    @Bean
    public DataSource druidDataSource(@Value("${jdbc.driverClassName}") String driver,
                                      @Value("${jdbc.url}") String url,
                                      @Value("${jdbc.username}") String username,
                                      @Value("${jdbc.password}") String password) {
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
    public PropertyPlaceholderConfigurer dbPropertiesConfigurer() {
        return new PropertyPlaceholderConfigurer();
    }
    
}

