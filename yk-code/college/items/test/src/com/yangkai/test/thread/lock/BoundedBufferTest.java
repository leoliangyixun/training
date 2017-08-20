package com.yangkai.test.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBufferTest {

	public BoundedBufferTest() {

	}

	public static void main(String[] args) {
		final MyBoundedBuffer buffer = new MyBoundedBuffer();
		// final BoundedBuffer buffer = new BoundedBuffer();
		// final APIBoundedBuffer buffer = new APIBoundedBuffer();
		/*
		 * for (int i = 0; i < 10; i++) { System.out.println(buffer.cache[i]); }
		 */

		for (int i = 1; i <= 100; i++) {
			final int index = i;

			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						buffer.take();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}).start();

			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						buffer.put(index);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}).start();

		}

	}

	public static class MyBoundedBuffer {
		private final int BUFFER_SIZE = 10;
		private Lock lock = new ReentrantLock();
		private Condition takeElem = lock.newCondition();
		private Condition putElem = lock.newCondition();
		private final Object[] cache = new Object[BUFFER_SIZE];

		private int head, tail, count;

		public MyBoundedBuffer() {

		}

		public void put(Object obj) throws InterruptedException {
			lock.lock();
			try {
				while (count == cache.length) {
					putElem.await();
				}
				cache[tail++] = obj;

				if (tail == cache.length) {
					tail = 0;
				}
				count++;
				System.out.println(" ��ǰ�����Ԫ��Ϊ��" + obj + " , " + " ͷ��¼ָ��λ�ã�"
						+ head + " , " + " β��¼ָ��λ�ã�" + tail + " , "
						+ " ��ǰ����Ԫ�ظ�����" + count);
				takeElem.signal();
			} finally {
				lock.unlock();
			}
		}

		public void take() throws InterruptedException {
			lock.lock();
			try {
				while (count == 0) {
					takeElem.await();
				}
				Object obj = cache[head];
				cache[head++] = null;

				if (head == cache.length) {
					head = 0;
				}
				count--;
				System.out.println(" ��ǰȡ����Ԫ��Ϊ��" + obj + " , " + " ͷ��¼ָ��λ�ã�"
						+ head + " , " + " β��¼ָ��λ�ã�" + tail + " , "
						+ " ��ǰ����Ԫ�ظ�����" + count);
				putElem.signal();
			} finally {
				lock.unlock();
			}

		}
	}

	public static class BoundedBuffer {
		private final int BUFFER_SIZE = 10;
		private Lock lock = new ReentrantLock();
		private Condition notFull = lock.newCondition();
		private Condition notEmpty = lock.newCondition();
		private final Object[] cache = new Object[BUFFER_SIZE];

		private int head, tail, count;

		public BoundedBuffer() {

		}

		public void put(Object obj) throws InterruptedException {
			lock.lock();
			try {
				while (count == cache.length) {
					notFull.await();
				}
				cache[tail] = obj;
				count++;
				if (++tail == cache.length) {
					tail = 0;
				}
				System.out.println(" ��ǰ�����Ԫ��Ϊ��" + obj + " , " + " ͷ��¼ָ��λ�ã�"
						+ head + " , " + " β��¼ָ��λ�ã�" + tail + " , "
						+ " ��ǰ����Ԫ�ظ�����" + count);
				notEmpty.signal();
			} finally {
				lock.unlock();
			}
		}

		public void take() throws InterruptedException {
			lock.lock();
			try {
				while (count == 0) {
					notEmpty.await();
				}
				Object obj = cache[head];
				cache[head] = null;
				count--;
				if (++head == cache.length) {
					head = 0;
				}
				System.out.println(" ��ǰȡ����Ԫ��Ϊ��" + obj + " , " + " ͷ��¼ָ��λ�ã�"
						+ head + " , " + " β��¼ָ��λ�ã�" + tail + " , "
						+ " ��ǰ����Ԫ�ظ�����" + count);
				notFull.signal();
			} finally {
				lock.unlock();
			}

		}
	}

	public static class APIBoundedBuffer {
		final Lock lock = new ReentrantLock();
		final Condition notFull = lock.newCondition();
		final Condition notEmpty = lock.newCondition();

		final Object[] items = new Object[10];
		int head, tail, count;

		public void put(Object x) throws InterruptedException {
			lock.lock();
			try {
				while (count == items.length)
					notFull.await();
				items[head] = x;
				if (++head == items.length)
					head = 0;
				++count;
				System.out.println(" ��ǰ�����Ԫ��Ϊ��" + x + " , " + " ͷ��¼ָ��λ�ã�"
						+ head + " , " + " β��¼ָ��λ�ã�" + tail + " , "
						+ " ��ǰ����Ԫ�ظ�����" + count);
				notEmpty.signal();
			} finally {
				lock.unlock();
			}
		}

		public void take() throws InterruptedException {
			lock.lock();
			try {
				while (count == 0)
					notEmpty.await();
				Object x = items[tail];
				if (++tail == items.length)
					tail = 0;
				--count;
				System.out.println(" ��ǰȡ����Ԫ��Ϊ��" + x + " , " + " ͷ��¼ָ��λ�ã�"
						+ head + " , " + " β��¼ָ��λ�ã�" + tail + " , "
						+ " ��ǰ����Ԫ�ظ�����" + count);
				notFull.signal();
			} finally {
				lock.unlock();
			}
		}
	}

}
