/**
 * 
 */
package com.yk.spring.boot.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.yk.spring.boot.mvc.filter.AuthFilter;
import com.yk.spring.boot.mvc.filter.FirstFilter;

/**
 * @author yangkai
 *
 */
@Configuration
public class FilterConfig {
    @Bean
    @Order(2)
    AuthFilter authFilter() {
        return new AuthFilter();
    }
    @Bean
    @Order(1)
    FirstFilter firstFilter() {
        return new FirstFilter();
    }

}
