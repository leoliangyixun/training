/**
 * 
 */
package com.curator.framework;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.api.GetChildrenBuilder;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.test.Timing;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


/**
 * @author yangkai
 *
 */
public class TestCuratorFramework extends TestCase {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
      
    }

    @Test
    public void test() {
        System.out.println(this.server);
    }
    
    @Test
    public void testConnectionState() throws Exception {

        Timing timing = new Timing();
        CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(), timing.session(), timing.connection(), new RetryOneTime(1));
        try {
           
            ConnectionStateListener listener = new ConnectionStateListener() {
                @Override
                public void stateChanged(CuratorFramework client, ConnectionState newState) {
                    System.out.println("connenction state changed to " + newState.name());
                }
            };
            client.getConnectionStateListenable().addListener(listener);
            client.start();
            Thread.sleep(4000);
            server.stop();
            Thread.sleep(4000);
           
        } finally {
            CloseableUtils.closeQuietly(client);
          
        }
    }
    @Test
    public void testNamespaceWithWatcher() throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(this.server.getConnectString())
                .namespace("curator.training")
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .connectionTimeoutMs(1000)
                .sessionTimeoutMs(1000)
                .build();

        client.start();

        CuratorWatcher watcher = new CuratorWatcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("watcher works ......" + event.getPath());
            }
        };
        client.create().forPath("/base");
        client.getChildren().usingWatcher(watcher).forPath("/base");
        client.create().forPath("/base/child");
        client.getChildren().usingWatcher(watcher).forPath("/base/child");
        client.create().forPath("/base/child2");

    }

    





}
