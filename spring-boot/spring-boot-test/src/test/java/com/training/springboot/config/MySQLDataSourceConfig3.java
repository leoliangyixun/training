
package com.training.springboot.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;

//@Configuration
//@PropertySource({"classpath:application.properties"})
public class MySQLDataSourceConfig3 {
    @Autowired
    private Environment environment;

    @Bean
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        druidDataSource.setUrl(environment.getProperty("jdbc.url"));
        druidDataSource.setUsername(environment.getProperty("jdbc.username"));
        druidDataSource.setPassword(environment.getProperty("jdbc.password"));
        try {
            druidDataSource.setFilters("stat, wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return druidDataSource;
    }
}

