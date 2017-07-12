/**
 * 
 */
package com.yk.spring.boot.demo.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yangkai
 *
 */
@Slf4j
@Component
public class JsApiTicketRefreshTask {

    //private ExecutorService executor = ThreadPool.newFixedThreadPool("refresh-jsapi-ticket", 20, 10);
    //private CompletionService<Void> completionService = new ExecutorCompletionService<Void>(executor);
    private ExecutorService executor = Executors.newFixedThreadPool(20);


    //@Scheduled(cron = "*/3 * * * * ? ")
    public void run()  throws Exception {
        long start = System.currentTimeMillis();
        System.out.println("--------------------------JsApiTicketRefreshTask Start: " + Thread.currentThread().getName());

        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                long start2 = System.currentTimeMillis();
                System.out.println("jsapi_ticket Start: " + Thread.currentThread().getName());

                Thread.sleep(5000);

                System.out.println("jsapi_ticket End: " + Thread.currentThread().getName() + " costs[" + (System.currentTimeMillis() - start2) + "] ms");

                return null;
            });

        }

        System.out.println("--------------------------JsApiTicketRefreshTask End: " + Thread.currentThread().getName() + " costs[" + (System.currentTimeMillis() - start) + "] ms");
    }

    //@Scheduled(cron = "*/3 * * * * ? ")
    //@Async
    public void run2()  throws Exception {
        long start = System.currentTimeMillis();
        System.out.println("--------------------------JsApiTicketRefreshTask Start: " + Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("--------------------------JsApiTicketRefreshTask End: " + Thread.currentThread().getName() + " costs[" + (System.currentTimeMillis() - start) + "] ms");
    }
}
