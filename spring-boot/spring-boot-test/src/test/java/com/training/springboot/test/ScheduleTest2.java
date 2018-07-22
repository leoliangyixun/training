package com.training.springboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @author yangkai
 * @date 2018/5/11
 * @email yangkai@hujiang.com
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ScheduleTest2 {

    //@Component
    public static class DefaultSchedulerConfigurer  implements SchedulingConfigurer {

        @Override
        public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
            ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
            scheduler.setPoolSize(20);
            scheduler.setThreadNamePrefix("task-");
            scheduler.setAwaitTerminationSeconds(60);
            scheduler.setWaitForTasksToCompleteOnShutdown(true);
            scheduler.setRemoveOnCancelPolicy(true);

            taskRegistrar.setTaskScheduler(scheduler);
            taskRegistrar.getCronTaskList().forEach(cronTask -> {
                System.out.println(cronTask.toString());
            });

        }
    }

    @Test
    public void test() {
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
    }

    @Component
    public static class Task {

        @Scheduled(fixedRate = 1000)
        public void scheduleFixedRateTask() throws Exception {
            System.out.println(Thread.currentThread().getName() + " Fixed rate task - " + new Date());
            Thread.sleep(2000);
        }
    }
}
