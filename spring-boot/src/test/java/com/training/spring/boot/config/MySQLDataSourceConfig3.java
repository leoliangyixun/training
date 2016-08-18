/**
 * 
 */
package com.training.spring.boot.config;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.alibaba.druid.pool.DruidDataSource;
import com.hujiang.basic.framework.rest.config.db.MybatisConfig;

/**
 * @author yangkai
 *
 */
@Configuration
@ComponentScan({ "com.hujiang.mmp.prv.service" })
@Import({MybatisConfig.class})
@PropertySource({"classpath:application.properties"})
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
