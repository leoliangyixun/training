package com.yk.concurrent;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MyAsyncService {

    static AtomicInteger taskNoCounter = new AtomicInteger();

    public MyAsyncService() {
    }

    @Async("asyncExecutor")
    public void performTask() {
        int delayMs = (int) (System.currentTimeMillis()%1000+1000);
        int taskNo = taskNoCounter.incrementAndGet();
        String taskInfo = "MyAsyncTask [taskNo=" + taskNo + ", delayMs=" + delayMs + ", threadId="+Thread.currentThread().getId()+"]";
        System.out.println("+ start " +taskInfo);
        try {
            Thread.sleep(delayMs);
        } catch (InterruptedException e) {
            // empty on purpose
        }
        System.out.println("- end   " +taskInfo);
    }

}