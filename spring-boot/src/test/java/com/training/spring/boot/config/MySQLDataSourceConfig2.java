/**
 * 
 *//*

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

*/
/**
 * @author yangkai
 *
 *//*

@Configuration
@ComponentScan({ "com.hujiang.mmp.prv.service" })
@Import({MybatisConfig.class})
@PropertySource({"classpath:application.properties"})
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
*/
