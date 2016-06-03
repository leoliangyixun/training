package com.curator.framework.recipes;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.RevocationListener;
import org.apache.curator.framework.recipes.locks.Revoker;
import org.apache.curator.retry.RetryOneTime;
import org.junit.Assert;
import org.junit.Test;

import com.curator.base.TestCase;

import java.util.Collection;
import java.util.concurrent.*;

/**
 * Created by yangkai on 16/6/2.
 */
public class TestInterProcessMutex extends TestCase{
    private static final String LOCK_PATH ="/locks/our-lock";

    @Test
    public void testRevoking() throws Exception {

        final CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(), new RetryOneTime(1));
        try {
            client.start();
            final InterProcessMutex lock = new InterProcessMutex(client, LOCK_PATH);

            ExecutorService executorService = Executors.newCachedThreadPool();

            final CountDownLatch revokeLatch = new CountDownLatch(1);
            final CountDownLatch lockLatch = new CountDownLatch(1);
            Future<Void> f1 = executorService.submit
                    (
                            new Callable<Void>() {
                                @Override
                                public Void call() throws Exception {
                                    RevocationListener<InterProcessMutex> listener = new RevocationListener<InterProcessMutex>() {
                                        @Override
                                        public void revocationRequested(InterProcessMutex lock) {
                                            revokeLatch.countDown();
                                        }
                                    };
                                    lock.makeRevocable(listener);
                                    System.out.println("f1 请求获取锁");
                                    lock.acquire();
                                    System.out.println("f1 获取锁");
                                    lockLatch.countDown();
                                    System.out.println("f1 等待 f2 发起撤销指令,f1 被阻塞");
                                    revokeLatch.await();
                                    System.out.println("f1 被撤销完成, f1激活");
                                    lock.release();
                                    System.out.println("f1 释放锁");
                                    return null;
                                }
                            }
                    );

            Future<Void> f2 = executorService.submit
                    (
                            new Callable<Void>() {
                                @Override
                                public Void call() throws Exception {
                                    System.out.println("f2 等待 f1 获取锁, f2 被阻塞");
                                    lockLatch.await(10, TimeUnit.SECONDS);

                                    Collection<String> nodes = lock.getParticipantNodes();
                                   // System.out.println("f2 发起撤销指令");
                                   // Revoker.attemptRevoke(client, nodes.iterator().next());
                                    InterProcessMutex l2 = new InterProcessMutex(client, LOCK_PATH);
                                    System.out.println("f2 请求获取锁");
                                    l2.acquire(5, TimeUnit.SECONDS);
                                    System.out.println("f2 获取锁");
                                    l2.release();
                                    System.out.println("f2 释放锁");
                                    return null;
                                }
                            }
                    );

            f2.get();
            f1.get();
        } finally {
            client.close();
        }
    }

}
