package com.training.springboot.test;

import com.training.springboot.test.ScheduleTest.Config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author yangkai
 * @date 2018/5/11
 * @email yangkai@hujiang.com
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Import({Config.class})
public class ScheduleTest {

    @Configuration
    @EnableScheduling
    public class Config {

        @Bean("taskScheduler")
        public Executor taskScheduler() {
            ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
            scheduler.setPoolSize(30);
            scheduler.setThreadNamePrefix("taskScheduler-");
            scheduler.setAwaitTerminationSeconds(60);
            scheduler.setWaitForTasksToCompleteOnShutdown(true);
            scheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            return scheduler;
        }

        @Bean("taskExecutor")
        public Executor taskExecutor() {
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

        @Scheduled(fixedRate = 1000)
        public void scheduleFixedRateTask() throws Exception {
            System.out.println(Thread.currentThread().getName() + " Fixed rate task - " + new Date());
            //Thread.sleep(2000);
        }
    }

    @Test
    public void test() {
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
    }

}
