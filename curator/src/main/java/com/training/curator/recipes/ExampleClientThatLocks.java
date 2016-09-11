/**
 * 
 */
package com.training.curator.recipes;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

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
public class ExampleClientThatLocks {


    //private  InterProcessSemaphoreMutex lock;
    private  InterProcessMutex lock;
    
    private  FakeLimitedResource resource;
    private  String clientName;
    
	
    public ExampleClientThatLocks(CuratorFramework client, String lockPath, FakeLimitedResource resource, String clientName) {
        this.resource = resource;
        this.clientName = clientName;
       // lock = new InterProcessSemaphoreMutex(client, lockPath);
        lock = new InterProcessMutex(client, lockPath);
    }

    public  void doWork(int j, long time, TimeUnit unit) throws Exception {
        if (lock.acquire(time, unit)) {
            try {
                System.out.println(clientName + " has the lock at loop" + j);
                resource.use(clientName); //access resource exclusively
            } finally {
                System.out.println(clientName + " releasing the locklock at loop" + j);
                lock.release(); // always release the lock in a finally block
            }
            return;
        }
        throw new IllegalStateException(clientName + " could not acquire the lock at loop" + j);
    }

}
