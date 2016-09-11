/**
 * 
 */
package com.curator.framework;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.*;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.test.InstanceSpec;
import org.apache.curator.test.TestingCluster;
import org.apache.curator.test.Timing;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.curator.base.TestCase;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
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

    @Test
    public void testNamespaceInBackground() throws Exception
    {
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder();
        CuratorFramework client = builder.connectString(server.getConnectString()).namespace("aisa").retryPolicy(new RetryOneTime(1)).build();
        client.start();
        try
        {

            CuratorListener listener = new CuratorListener()
            {
                @Override
                public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception
                {
                    System.out.println("event received ......" + event);

                }
            };

            client.getCuratorListenable().addListener(listener);
            client.create().inBackground().forPath("/base");
            client.checkExists().inBackground().forPath("/base");



            //client.getCuratorListenable().removeListener(listener);

            BackgroundCallback callback = new BackgroundCallback()
            {
                @Override
                public void processResult(CuratorFramework client, CuratorEvent event) throws Exception
                {
                    System.out.println("process result ......" + event);

                }
            };
            client.getChildren().inBackground(callback).forPath("/base");

        }
        finally
        {
            CloseableUtils.closeQuietly(client);

        }
    }

    @Test
    public void testBackgroundCreate() throws Exception
    {
        CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(), new RetryOneTime(1));
        client.start();
        try
        {
            client.getCuratorListenable().addListener
                    (
                            new CuratorListener()
                            {
                                @Override
                                public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception
                                {
                                    System.out.println(event);

                                }
                            }
                    );

            CountDownLatch latch = new CountDownLatch(1);
            client.create().inBackground(latch).forPath("/test");
            latch.await(2, TimeUnit.SECONDS);
            //latch.await();
            System.out.println("block over ......");
        }
        finally
        {
            CloseableUtils.closeQuietly(client);
        }
    }

    @Test
    public void testBackgroundDeleteWithChildren() throws Exception
    {
        CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(), new RetryOneTime(1));
        client.start();
        try
        {
            client.getCuratorListenable().addListener
                    (
                            new CuratorListener()
                            {
                                @Override
                                public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception
                                {
                                    System.out.println("event received ......" + event);
                                }
                            }
                    );

            client.create().creatingParentsIfNeeded().forPath("/one/two/three/four");

            Stat stat2 = client.checkExists().forPath("/one/two");
            System.out.println("stat2: " + stat2);

            client.delete().deletingChildrenIfNeeded().inBackground(new Object()).forPath("/one/two/three/four");

            Stat stat3 = client.checkExists().forPath("/one/two");
            System.out.println("stat3: " + stat3);

        }
        finally
        {
            CloseableUtils.closeQuietly(client);
        }
    }

    @Test
    public void testCreate() throws Exception
    {
        CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(), new RetryOneTime(1));
        client.start();
        try
        {
            client.getCuratorListenable().addListener
                    (
                            new CuratorListener()
                            {
                                @Override
                                public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception
                                {
                                    System.out.println("event received ......" + event);
                                }
                            }
                    );

            client.create().inBackground().forPath("/base");
           // client.create().forPath("/base");
           // client.create().forPath("/tree");

        }
        finally
        {
            CloseableUtils.closeQuietly(client);
        }
    }
    
    @Test
    public void testCluster() throws Exception {
        String connectionString = "127.0.0.1:62754,127.0.0.1:62757,127.0.0.1:62760";
        CuratorFramework client = null;
        TestingCluster cluster = new TestingCluster(3);
        cluster.start();
        client = CuratorFrameworkFactory.newClient(connectionString, new RetryOneTime(1));
        client.start();
        System.out.println(client);
        for (InstanceSpec instanceSpec : cluster.getInstances()) {
            client = CuratorFrameworkFactory.newClient(instanceSpec.getConnectString(), new RetryOneTime(1));
            System.out.println(instanceSpec.getConnectString());
            client.start();
            Stat stat = client.checkExists().forPath("/");
            System.out.println("stat:  " + stat);
        }

    }


}
