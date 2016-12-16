/**
 * 
 */
package com.yk.spring.boot.demo.component;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author yangkai
 *
 */
@Component
public class ScheduleTask {
    
   @Scheduled(cron = "*/1 * * * * ? ")
    //@Scheduled(fixedRate = 2000)
    @Async(value = "taskScheduler")
    public void execute() {
        long start = System.currentTimeMillis();
        System.out.println("Start------> thead name: " +  Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (Exception e) {

        }
        System.out.println("End------>thead name: " +  Thread.currentThread().getName() + " costs[" + (System.currentTimeMillis() - start)+" ms]");

    }

}
