package com.training.springboot.test.async.task;
/**
 * 
 */


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author yangkai
 *
 */
@Component
public class ScheduleTask {
    private ExecutorService executor =Executors.newFixedThreadPool(10);
    
    //@Scheduled(cron = "*/1 * * * * ? ")
    public void start() throws Exception{
        this.schedule();
    }
    
    @Async
    public  void schedule( )  throws Exception {
        long start = System.currentTimeMillis();
        System.out.println("ScheduleTask Start------> thread name: " +  Thread.currentThread().getName());
        Thread.sleep(5000);
         System.out.println("ScheduleTask End------>thread name: " +  Thread.currentThread().getName() + " costs[" + (System.currentTimeMillis() - start) + " ms]");
    }
    
    //@Scheduled(cron = "*/5 * * * * ? ")
    //@Async
    public void schedule2()  throws Exception {
        long start = System.currentTimeMillis();
        System.out.println("--------------------------Demo Start: " + Thread.currentThread().getName());
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                long start2 = System.currentTimeMillis();
                System.out.println("jsapi_ticket Start: " + Thread.currentThread().getName());

                Thread.sleep(5000);

                System.out.println("jsapi_ticket End: " + Thread.currentThread().getName() + " costs[" + (System.currentTimeMillis() - start2) + "] ms");
                latch.countDown();
                return null;
            });

        }

        latch.await();
        System.out.println("--------------------------Demo End: " + Thread.currentThread().getName() + " costs[" + (System.currentTimeMillis() - start) + "] ms");
    }


    //@Scheduled(cron = "*/3 * * * * ? ")
    public void run()  throws Exception {
        long start = System.currentTimeMillis();
        System.out.println("--------------------------Demo Start: " + Thread.currentThread().getName());

        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                long start2 = System.currentTimeMillis();
                System.out.println("jsapi_ticket Start: " + Thread.currentThread().getName());

                Thread.sleep(5000);

                System.out.println("jsapi_ticket End: " + Thread.currentThread().getName() + " costs[" + (System.currentTimeMillis() - start2) + "] ms");

                return null;
            });

        }

        System.out.println("--------------------------Demo End: " + Thread.currentThread().getName() + " costs[" + (System.currentTimeMillis() - start) + "] ms");
    }

    //@Scheduled(cron = "*/3 * * * * ? ")
    //@Async
    public void run2()  throws Exception {
        long start = System.currentTimeMillis();
        System.out.println("--------------------------Demo Start: " + Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("--------------------------Demo End: " + Thread.currentThread().getName() + " costs[" + (System.currentTimeMillis() - start) + "] ms");
    }

}
