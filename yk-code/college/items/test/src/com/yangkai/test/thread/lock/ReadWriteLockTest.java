package com.yangkai.test.thread.lock;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

	public ReadWriteLockTest() {

	}

	public static void main(String[] args) {
		final Data data = new Data();
		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						data.get();
					}
				}

			}).start();
			new Thread(new Runnable() {

				@Override
				public void run() {
					while(true){
						data.put(new Random().nextInt(100)+1);
					}
				}
			}).start();
		}

	}

}

class Data {
	private Integer data;
	private ReadWriteLock rwLock=new ReentrantReadWriteLock();
	public void put(int num) {
		rwLock.writeLock().lock();
		try{
			System.out.println(Thread.currentThread().getName()+" is ready to put data ");
			this.data=num;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" has put data "+data);
		}finally{
			rwLock.writeLock().unlock();
		}
	}

	public void get() {
		rwLock.readLock().lock();
		try{
			System.out.println(Thread.currentThread().getName()+" is ready to get data ");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" has got data "+data);
		}finally{
			rwLock.readLock().unlock();
		}
	}
}
