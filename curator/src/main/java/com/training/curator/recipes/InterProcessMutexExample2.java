/**
 * 
 */
package com.training.curator.recipes;

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
public class InterProcessMutexExample2 {
    private static final String PATH = "/examples/locks";
	public InterProcessMutexExample2() {
		
	}

	public static void main(String[] args) throws Exception {
        final FakeLimitedResource resource = new FakeLimitedResource();
        ExecutorService service = Executors.newFixedThreadPool(5);
        final TestingServer server = new TestingServer();
/*        final CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(), new ExponentialBackoffRetry(1000, 3));
        client.start();*/
        try {
            for (int i = 0; i < 20; ++i) {
                final int index = i;
				final int x = (index % 2 == 0 ? 0 : 1);
                Callable<Void> task = new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                    	CuratorFramework client = null;
                    
                        try {
                        
                        	client = CuratorFrameworkFactory.newClient(server.getConnectString(), new ExponentialBackoffRetry(1000, 3));
                            client.start();
                            final ExampleClientThatLocks2 example = new ExampleClientThatLocks2(client, PATH+x, resource, "Client " + index);
                       
                               // example.doWork(j,1, TimeUnit.SECONDS);
                            // example.doWork(j);
                                example.doWork();
                           
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
