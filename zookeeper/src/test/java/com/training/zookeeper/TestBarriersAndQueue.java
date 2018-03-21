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
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.ZKDatabase;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * @description: Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年6月24日
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年6月24日       jiqingchuan          1.0             Why & What is modified
 */
public class TestBarriersAndQueue {
    ZooKeeper zk = null;
    @Test
    public void test() throws IOException, KeeperException, InterruptedException {
        //172.16.11.143:2181
        ZooKeeper zk = new ZooKeeper("172.16.11.143:2181", 1000, new Watcher() {

            @Override
            public void process(WatchedEvent event) {
                System.out.println("watcher worked, type: " + event.getType());

            }
        });

        zk.create("/master", "master".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.create("/master/s1", "s1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        //System.out.println(new String(zk.getData("/testRootPath",false,null))); 

        //System.out.println(zk.getChildren("/testRootPath",true)); 

        //zk.setData("/testRootPath/testChildPathOne","modifyChildDataOne".getBytes(),-1); 
        // System.out.println("目录节点状态：["+zk.exists("/testRootPath",true)+"]");

        // zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(),Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        // System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo",true,null)));

        // zk.delete("/testRootPath/testChildPathTwo",-1);
        List<String> list = zk.getChildren("/master", true);

        zk.close();

    }

    @Test
    public void test2() throws UnknownHostException {
        System.out.println(new String(InetAddress.getLocalHost().getCanonicalHostName().toString()));

    }

    @Test
    public void test3() throws IOException, KeeperException, InterruptedException {

       zk = new ZooKeeper("172.16.11.143:2181", 1000, new Watcher() {

            @Override
            public void process(WatchedEvent event) {
                System.out.println("watcher worked, type: " + event.getType());

            }
        });

        zk.getData("/master",false,null);

        zk.getChildren("/master",true);

       
        zk.exists("/master",true);

        zk.create("/master/s2", "s2".getBytes(),Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        zk.getData("/master/s2",true,null);

        zk.delete("/master/s2",-1);
        zk.delete("/master/s1",-1);

        zk.delete("/master",-1);

        zk.close();
    }

    @Test
    public void clear() throws IOException, KeeperException, InterruptedException{
       // zk.delete("/master/s2",-1);
       // zk.delete("/master/s1",-1);


    }


    @Test
    public void testBarrier() {


    }

    public static class SyncPrimitive implements Watcher {

        static ZooKeeper zk = null;
        static Integer mutex;
        String root;
        String name;

        public SyncPrimitive(String address, String root) {
            if (this.zk == null) {
                try {
                    System.out.println("Starting ZK:");
                    this.zk = new ZooKeeper(address, 3000, this);
                    this.mutex = new Integer(-1);
                    this.root = root;
                    this.name = new String(InetAddress.getLocalHost().getCanonicalHostName().toString());

                    Stat s = this.zk.exists(this.root, false);
                    if (s == null) {
                        this.zk.create(root, new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                    }

                    System.out.println("Finished starting ZK: " + this.zk);
                } catch (UnknownHostException e) {
                    System.out.println(e.toString());
                } catch (IOException e) {
                    System.out.println(e.toString());
                    this.zk = null;
                } catch (KeeperException e) {
                    System.out
                            .println("Keeper exception when instantiating queue: "
                                    + e.toString());
                } catch (InterruptedException e) {
                    System.out.println("Interrupted exception");
                }
            }
        }

        @Override
        public void process(WatchedEvent event) {
            synchronized (mutex) {
                System.out.println("watcher works: " + event.getType());
                mutex.notify();
            }

        }

    }

    public static class Barrier extends SyncPrimitive {
        int size;

        /**
         * note: the parameter address is needed, because the class Barrier extends SyncPrimitive
         *
         * @param address
         * @param size
         * @param root
         */
        public Barrier(String address, int size, String root) {
            super(address, root);
            this.size = size;
            this.root = root;

        }
    }

    static public class Queue extends SyncPrimitive {
        public Queue(String address, String root) {
            super(address, root);
            this.root = root;


        }
    }

}
