/**
 * 
 */
package com.yk.spring.boot.demo.config;

import java.util.concurrent.ExecutorService;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author yangkai
 *
 */
@Configuration
@EnableScheduling
@EnableAsync
@EnableConfigurationProperties
@PropertySource({ "classpath:application.properties" })
public class TestConfig {


    @Bean
    public ExecutorService threadPool() {
        return null;
    }

    @Bean("taskScheduler")
    public ThreadPoolTaskScheduler taskScheduler(){

        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();

        taskScheduler.setPoolSize(5);
        return  taskScheduler;
    }




        //don't work
/*    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(25);
        return taskExecutor;
    }*/

}
