/**
 * 
 */
package com.training.activiti.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author yangkai
 *
 */
@Configuration
@EnableAspectJAutoProxy
public class ActivitiConfig {
	
	@Bean
	public DataSource datasource() {
	    return DataSourceBuilder.create()
    		.driverClassName("org.h2.Driver")
    		.url("jdbc:h2:mem:testdb;MODE=MySQL")
    		.username("sa")
    		.password("")
	        .build();
	}
}
