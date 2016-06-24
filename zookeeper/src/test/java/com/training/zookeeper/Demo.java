/**
 * 
 */
package com.training.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

/**
 * @description:
 * Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年6月24日
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年6月24日       jiqingchuan          1.0             Why & What is modified
 */
public class Demo {
    
    @Test
    public void test() throws IOException, KeeperException, InterruptedException {
        //172.16.11.143:2181
        ZooKeeper zk = new ZooKeeper("172.16.11.143:2181", 1000, new Watcher() {

            @Override
            public void process(WatchedEvent event) {
                System.out.println("watcher worked, type: " + event.getType());

            }
        });

        zk.create("/testRootPath", "testRootData".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(),
                Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);

    }

}
