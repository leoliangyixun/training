/**
 * 
 */
package com.curator.framework;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.test.Timing;
import org.apache.curator.utils.CloseableUtils;
import org.junit.Assert;
import org.junit.Test;


import com.curator.base.TestCase;

/**
 * @author yangkai
 *
 */
public class TestFramework extends TestCase {
    @Test
    public void testConnectionState() throws Exception
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
                    System.out.println("**connection state changed** " + newState.name());
                    queue.add(newState);
                }
            };
            client.getConnectionStateListenable().addListener(listener);

            client.start();
            Thread.sleep(4000);
            //Assert.assertEquals(queue.poll(timing.multiple(4).seconds(), TimeUnit.SECONDS), ConnectionState.CONNECTED);
            server.stop();
            Thread.sleep(4000);
           // Thread.sleep(4000);
            //Assert.assertEquals(queue.poll(timing.multiple(4).seconds(), TimeUnit.SECONDS), ConnectionState.SUSPENDED);
            //Assert.assertEquals(queue.poll(timing.multiple(4).seconds(), TimeUnit.SECONDS), ConnectionState.LOST);
           // Thread.sleep(10000);
        }
        finally
        {
            CloseableUtils.closeQuietly(client);
        }

    }

}
