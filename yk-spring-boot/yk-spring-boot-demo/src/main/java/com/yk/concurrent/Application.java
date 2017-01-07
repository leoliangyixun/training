package com.yk.concurrent;
import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableScheduling
@ComponentScan()
@EnableAsync
public class Application implements AsyncConfigurer{

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class);
    }

    @Override
    @Bean(name="asyncExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor exec = new ThreadPoolTaskExecutor();
        exec.setMaxPoolSize(Runtime.getRuntime().availableProcessors()*2);
        exec.setThreadGroupName("MyCustomExecutor");
        exec.setCorePoolSize(5);
        exec.setWaitForTasksToCompleteOnShutdown(true);
        exec.setBeanName("asyncExecutor");
        exec.initialize();
        return exec;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }
}