/**
 * 
 */
package com.curator.recipes;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import org.apache.curator.utils.CloseableUtils;

/**
 * @description:
 * Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年4月1日
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年4月1日       jiqingchuan          1.0             Why & What is modified
 */
public class InterProcessMutexTest {
    private static final String PATH = "/examples/locks";
	public InterProcessMutexTest() {
		
	}

	public static void main(String[] args) throws Exception {
        final FakeLimitedResource resource = new FakeLimitedResource();
        ExecutorService service = Executors.newFixedThreadPool(5);
        final TestingServer server = new TestingServer();
        try {
            for (int i = 0; i < 5; ++i) {
                final int index = i;
                Callable<Void> task = new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(), new ExponentialBackoffRetry(1000, 3));
                        
                        try {
                            client.start();
                            final ExampleClientThatLocks2 example = new ExampleClientThatLocks2(client, PATH, resource, "Client " + index);
                            for (int j = 0; j < 2; ++j) {
                                example.doWork(j,1, TimeUnit.SECONDS);
                            }
                        } catch (Throwable e) {
                           System.out.println("exception:" + e.getMessage());
                        } finally {
                            CloseableUtils.closeQuietly(client);
                        }
                        return null;
                    }
                };
                service.submit(task);
            }
            service.shutdown();
            service.awaitTermination(10, TimeUnit.MINUTES);
        } finally {
            CloseableUtils.closeQuietly(server);
        }
	}

}
