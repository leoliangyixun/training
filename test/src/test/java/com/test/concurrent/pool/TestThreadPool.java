/**
 * 
 */
package com.test.concurrent.pool;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yangkai
 *
 */
public class TestThreadPool {
    private ExecutorService service = Executors.newFixedThreadPool(100);
    private CompletionService<String> completionService = new ExecutorCompletionService<String>(service);

    @Test
    public void test () {

        
        completionService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
           //TODO
                return null;
            }
        });
    }

}
