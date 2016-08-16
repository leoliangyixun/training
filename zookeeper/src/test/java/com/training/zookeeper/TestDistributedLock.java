/**
 *
 */
package com.training.zookeeper;

import org.apache.zookeeper.*;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @description: Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年6月28日
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年6月28日       jiqingchuan          1.0             Why & What is modified
 */
public class TestDistributedLock {
    @Test
    public void test() throws IOException, KeeperException, InterruptedException {
        DistributedLock lock = new DistributedLock();
        lock.acquire();
        //System.out.println(lock.exists("/disLocks/sub0000000004"));

    }



    public static class DistributedLock implements Watcher {

        private static ZooKeeper zk;
        private static final int SESSION_TIMEOUT = 10000;
        private static final String GROUP_PATH = "/disLocks";
        private static final String SUB_PATH = "/disLocks/sub";
        private static final String CONNECTION_STRING = "172.16.11.143:2181";


        public DistributedLock() {
;
            try {
                zk = new ZooKeeper(CONNECTION_STRING, SESSION_TIMEOUT, this);
                if (zk.exists(GROUP_PATH, true) == null) {
                    String path = zk.create(GROUP_PATH, GROUP_PATH.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                    System.out.println(path);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }
        }

        @Override
        public void process(WatchedEvent event) {

        }

        public void acquire() throws KeeperException, InterruptedException {
            String path = zk.create(SUB_PATH, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

            String s = path.substring(GROUP_PATH.length() + 1);
            //System.out.println(s);
            System.out.println(path);
            List<String> paths = zk.getChildren(GROUP_PATH, true);
            System.out.println(paths);

        }

        public boolean exists(String path) throws KeeperException, InterruptedException {
            return zk.exists(path, true) == null ? false : true;

        }


    }
}
