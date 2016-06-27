/**
 * 
 */
package com.training.zookeeper;

import java.io.IOException;
import java.util.List;

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
        // 创建一个目录节点
        zk.create("/testRootPath", "testRootData".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL); 
        // 创建一个子目录节点
        zk.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(),Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL); 
        System.out.println(new String(zk.getData("/testRootPath",false,null))); 
        // 取出子目录节点列表
        System.out.println(zk.getChildren("/testRootPath",true)); 
        // 修改子目录节点数据
        zk.setData("/testRootPath/testChildPathOne","modifyChildDataOne".getBytes(),-1); 
        System.out.println("目录节点状态：["+zk.exists("/testRootPath",true)+"]"); 
        // 创建另外一个子目录节点
        zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(), 
          Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL); 
        System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo",true,null))); 
        // 删除子目录节点
        zk.delete("/testRootPath/testChildPathTwo",-1); 
        zk.delete("/testRootPath/testChildPathOne",-1); 
        // 删除父目录节点
        zk.delete("/testRootPath",-1); 
        // 关闭连接
        zk.close();

    }

}
