/**
 * 
 */
package com.yk.spring.boot.mvc.test.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author yangkai
 *
 */
@Configuration
@PropertySource({ "classpath:application.properties" })
public class TestConfig {
	
	@PostConstruct
	public void init() {
		System.out.println("xxxxxx");
	}

}
