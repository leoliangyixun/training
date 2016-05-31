/**
 * 
 */
package com.curator.framework;

import static org.junit.Assert.*;

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
  
        CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(), new RetryOneTime(1));
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
    public void testConnectionState2() throws Exception
    {
        Timing timing = new Timing();
        CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(), timing.session(), timing.connection(), new RetryOneTime(1));
        try
        {
            final BlockingQueue<ConnectionState> queue = new LinkedBlockingQueue<ConnectionState>();
            ConnectionStateListener listener = new ConnectionStateListener()
            {
                @Override
                public void stateChanged(CuratorFramework client, ConnectionState newState)
                {
                    queue.add(newState);
                    System.out.println("connenction state changed to " + newState.name());
                }
            };
            client.getConnectionStateListenable().addListener(listener);

            client.start();
            //Assert.assertEquals(queue.poll(timing.multiple(4).seconds(), TimeUnit.SECONDS), ConnectionState.CONNECTED);
            Thread.sleep(4000);
            server.stop();
            Thread.sleep(4000);
            Thread.sleep(4000);
            //Assert.assertEquals(queue.poll(timing.multiple(4).seconds(), TimeUnit.SECONDS), ConnectionState.SUSPENDED);
            //Assert.assertEquals(queue.poll(timing.multiple(4).seconds(), TimeUnit.SECONDS), ConnectionState.LOST);
        }
        finally
        {
            CloseableUtils.closeQuietly(client);
        }
    }

}
