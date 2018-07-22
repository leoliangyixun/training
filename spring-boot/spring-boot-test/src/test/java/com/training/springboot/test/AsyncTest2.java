package com.training.springboot.test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

import org.junit.runner.RunWith;
import org.slf4j.MDC;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.concurrent.Executor;

/**
 * @author yangkai
 * @date 2018/5/11
 * @email yangkai@hujiang.com
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class AsyncTest2 {

    @Slf4j
    @EnableAsync
    @Configuration
    public static class AsyncConfig implements AsyncConfigurer {

        @Bean("taskExecutor")
        @Override
        public Executor getAsyncExecutor() {
            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(10);
            executor.setMaxPoolSize(10);
            executor.setQueueCapacity(100);
            executor.setThreadNamePrefix("async-pool-");
            executor.setTaskDecorator(new MdcTaskDecorator());
            executor.setWaitForTasksToCompleteOnShutdown(true);
            executor.initialize();
            return executor;
        }

        public static class MdcTaskDecorator implements TaskDecorator {
            @Override
            public Runnable decorate(Runnable runnable) {
                Map<String, String> contextMap = MDC.getCopyOfContextMap();
                try {
                    if (contextMap != null) {
                        MDC.setContextMap(contextMap);
                    }
                    runnable.run();
                } finally {
                    /** 清理后会导致父线程的上下文清空,进入时会复制父线程的内容进行覆盖,可不清理 */
                    //MDC.clear();
                }
                return runnable;
            }
        }

        @Override
        public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
            return (throwable, method, params) -> {
                log.error("异步任务异常：方法：{} 参数：{}", method.getName(), JSON.toJSONString(params));
                log.error(throwable.getMessage(), throwable);
            };
        }
    }

    @Slf4j
    @Configuration
    @EnableScheduling
    public static class SchedulingConfig implements SchedulingConfigurer {

        @Override
        public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
            scheduledTaskRegistrar.setTaskScheduler(taskScheduler());
        }

        @Bean(destroyMethod = "shutdown")
        public ThreadPoolTaskScheduler taskScheduler() {
            ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
            scheduler.setPoolSize(5);
            scheduler.setThreadNamePrefix("dispatch-");
            scheduler.setAwaitTerminationSeconds(600);
            scheduler.setErrorHandler(throwable -> log.error("调度任务发生异常", throwable));
            scheduler.setWaitForTasksToCompleteOnShutdown(true);
            return scheduler;
        }

    }


}
