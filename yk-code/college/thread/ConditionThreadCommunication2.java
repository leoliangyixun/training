package com.yangkai.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionThreadCommunication2 {
	public static boolean shouldMain = true;

	public ConditionThreadCommunication2() {

	}

	public static void main(String[] args) {
		/**
		 * 需求：Main线程与Sub线程交替执行，但必须等其中一个线程某一次循环执行完之后另一个线程才能执行。
		 * */
		Lock lock = new ReentrantLock();
		Condition cond = lock.newCondition();
		new Thread(new Sub(lock, cond)).start();
		new Thread(new Main(lock, cond)).start();
	}

	public static class Main implements Runnable {
		private Lock lock;
		private Condition cond;

		public Main(Lock lock, Condition cond) {
			this.lock = lock;
			this.cond = cond;
		}

		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				this.output(i);
			}
		}

		public void output(int i) {
			lock.lock();
			try {
				while(true){
					if (ConditionThreadCommunication2.shouldMain) {
						for (int j = 0; j < 5; j++) {
							System.out.println("Main thread: inner loop of " + (j + 1)
									+ " in outer loop of " + (i + 1));
						}
						ConditionThreadCommunication2.shouldMain = false;
						cond.signal();
						break;
					}
					try {
						cond.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} finally {
				lock.unlock();
			}
		}

	}

	public static class Sub implements Runnable {
		private Lock lock;
		private Condition cond;

		public Sub(Lock lock, Condition cond) {
			this.lock = lock;
			this.cond = cond;
		}

		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				this.output(i);
			}
		}

		public void output(int i) {
			lock.lock();
			try {
				while(true){
					if (!ConditionThreadCommunication2.shouldMain) {
						for (int j = 0; j < 5; j++) {
							System.out.println("Sub thread : inner loop of "
									+ (j + 1) + " in outer loop of " + (i + 1));
						}
						ConditionThreadCommunication2.shouldMain = true;
						cond.signal();
						break;
					}
					try {
						cond.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} finally {
				lock.unlock();
			}
		}
	}
}
