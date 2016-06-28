/**
 * 
 */
package com.training.zookeeper;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

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
       
        zk.create("/testRootPath", "testRootData".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
        zk.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(),Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
        
        //System.out.println(new String(zk.getData("/testRootPath",false,null))); 
       
        //System.out.println(zk.getChildren("/testRootPath",true)); 
       
        //zk.setData("/testRootPath/testChildPathOne","modifyChildDataOne".getBytes(),-1); 
       // System.out.println("目录节点状态：["+zk.exists("/testRootPath",true)+"]"); 
       
       // zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(),Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL); 
       // System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo",true,null))); 
       
       // zk.delete("/testRootPath/testChildPathTwo",-1); 
        zk.delete("/testRootPath/testChildPathOne",-1); 
        zk.delete("/testRootPath",-1); 
        zk.close();

    }
    
    @Test
    public void test2() throws UnknownHostException {
        System.out.println(new String(InetAddress.getLocalHost().getCanonicalHostName().toString()));
        
    }
    
    @Test
    public void testBarrier()  {

        
        SyncPrimitive sp = new SyncPrimitive("");
        
        
        
        
        
        
        

        
    }
    public static class SyncPrimitive implements Watcher {
        private static ZooKeeper zk = null;
        private static Integer mutex;

        private String root;

        SyncPrimitive(String address) {
            if(zk == null){
                try {
                    System.out.println("Starting ZK:");
                    zk = new ZooKeeper(address, 3000, this);
                    mutex = new Integer(-1);
                    System.out.println("Finished starting ZK: " + zk);
                } catch (IOException e) {
                    System.out.println(e.toString());
                    zk = null;
                }
            }
        }

        public synchronized  void process(WatchedEvent event) {
            synchronized (mutex) {
                mutex.notify();
            }
        }
    }

}
