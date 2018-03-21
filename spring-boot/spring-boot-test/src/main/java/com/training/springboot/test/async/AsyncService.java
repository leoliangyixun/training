package com.training.springboot.test.async;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {
    private static final  AtomicInteger taskNoCounter = new AtomicInteger();

    //@Async("asyncExecutor")
    public void performTask() {
        int delayMs = (int) (System.currentTimeMillis()%1000+1000);
        int taskNo = taskNoCounter.incrementAndGet();
        String taskInfo = "MyAsyncTask [taskNo=" + taskNo + ", delayMs=" + delayMs + ", threadId="+Thread.currentThread().getId()+"]";
        System.out.println("+ start " +taskInfo);
        try {
            Thread.sleep(delayMs);
        } catch (InterruptedException e) {
           
        }
        System.out.println("- end   " +taskInfo);
    }
    
    @Async
    //@Async("asyncExecutor")
    public void start() {
        System.out.println(Thread.currentThread().getName() + " start async......");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
         
        System.out.println(Thread.currentThread().getName() + " end async......");
    }


  
}
