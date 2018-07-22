package com.training.springboot.test;

import com.training.springboot.test.ScheduleTest3.Config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@RunWith(SpringJUnit4ClassRunner.class)
@Import(Config.class)
public class ScheduleTest3 {

    @Configuration
    @EnableScheduling
    @EnableAsync
    public static class Config {
        @Bean("taskScheduler")
        public TaskScheduler taskScheduler() {
            ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
            scheduler.setPoolSize(30);
            scheduler.setThreadNamePrefix("taskScheduler-");
            scheduler.setAwaitTerminationSeconds(60);
            scheduler.setWaitForTasksToCompleteOnShutdown(true);
            scheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            return scheduler;
        }

        @Bean("taskExecutor")
        public TaskExecutor taskExecutor() {
            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(10);
            executor.setMaxPoolSize(20);
            executor.setQueueCapacity(200);
            executor.setKeepAliveSeconds(60);
            executor.setAwaitTerminationSeconds(60);
            executor.setWaitForTasksToCompleteOnShutdown(true);
            executor.setThreadNamePrefix("taskExecutor-");
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            return executor;
        }

    }

    @Component
    public static class Task {
        //private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss SSS");
        //@Scheduled(fixedRate = 1000)
        public void run1() throws Exception {
            System.out.println(sdf.format(new Date()) + " start run1 " + Thread.currentThread().getName());
            Thread.sleep(2000);
            //Thread.sleep(100);
            System.out.println(sdf.format(new Date()) + " end run1 " + Thread.currentThread().getName());
        }

        //@Scheduled(fixedRate = 1000)
        public void run2() throws Exception {
            System.out.println(sdf.format(new Date()) + " start run2 " + Thread.currentThread().getName());
            Thread.sleep(2000);
            //Thread.sleep(100);
            System.out.println(sdf.format(new Date()) + " end run2 " + Thread.currentThread().getName());
        }

        //@Scheduled(fixedDelay = 1000)
        public void run3() throws Exception {
            System.out.println(sdf.format(new Date()) + " start run3 " + Thread.currentThread().getName());
            Thread.sleep(2000);
            System.out.println(sdf.format(new Date()) + " end run3 " + Thread.currentThread().getName());
        }

        //@Async("taskExecutor")
        //@Scheduled(fixedRate = 1000)
        @Scheduled(fixedDelay = 1000)
        public void run4() throws Exception {
            System.out.println(sdf.format(new Date()) + " start run4 " + Thread.currentThread().getName());
            Thread.sleep(2000);
            System.out.println(sdf.format(new Date()) + " end run4 " + Thread.currentThread().getName());
        }
    }

    @Autowired
    private Task task;

    @Test
    public void test1() {
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
    }


}
