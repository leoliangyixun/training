package com.test.concurrent;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestInterrupt {
	
	
	@Test
	public void testInterrupt() {
		Thread i1 = new Thread(new RunIt3());
		Thread i2 = new Thread(new RunIt3());
		i1.start();
		i2.start();
	    i2.interrupt();
        // 保证前面的线程都执行完
        while (Thread.activeCount() > 1) { 
            Thread.yield();
        }
		
	}
	
	public static class RunIt3 implements Runnable {
		private static Lock lock = new ReentrantLock();

		public void run() {
			try {

			//lock.lock();
				 lock.lockInterruptibly();
				// lock.tryLock();
			//	lock.tryLock(1, TimeUnit.SECONDS);

				System.out.println(Thread.currentThread().getName() + " running");
				TimeUnit.SECONDS.sleep(3);

				System.out.println(Thread.currentThread().getName() + " finished");
			
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + " interrupted");

			} finally {
				lock.unlock();
			}

		}

	}
		  

}
