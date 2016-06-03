/**
 * 
 */
package com.curator.framework.recipes;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

import com.curator.base.TestCase;

/**
 * @description:
 * Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年6月3日
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年6月3日       jiqingchuan          1.0             Why & What is modified
 */
public class TestInterProcessReadWriteLock extends TestCase {
    
    private  CuratorFramework client = null;  
    private   String PATH = "/locks";  
  
    // 进程内部（可重入）读写锁  
    private   InterProcessReadWriteLock lock;  
    // 读锁  
    private   InterProcessLock readLock;  
    // 写锁  
    private   InterProcessLock writeLock;  
    static {

    }
    
    @Test
    public void testReadWriteLock() {
        client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:62754,127.0.0.1:62757,127.0.0.1:62760")  
                .sessionTimeoutMs(30000)  
                .connectionTimeoutMs(30000)  
                .canBeReadOnly(false)  
                .retryPolicy(new ExponentialBackoffRetry(1000, Integer.MAX_VALUE))  
                .namespace("curator")  
                .defaultData(null)  
                .build();
        client.start(); 
        
        lock = new InterProcessReadWriteLock(client, PATH);
        readLock = lock.readLock();
        writeLock = lock.writeLock();
    }

}
