package com.yangkai.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ThreadScopeGetData {
	private static int data = 0;
	private static Map<Thread, Integer> dataMap = new HashMap<Thread, Integer>();

	static class A {
		public void getData() {
			System.out.println("A gets " + dataMap.get(Thread.currentThread())
					+ " from dataMap in " + Thread.currentThread().getName());
		}
	}

	static class B {
		public void getData() {
			System.out.println("B gets " + dataMap.get(Thread.currentThread())
					+ " from dataMap in " + Thread.currentThread().getName());
		}
	}

	static class C {
		public void getData() {
			System.out.println("C gets " + dataMap.get(Thread.currentThread())
					+ " from dataMap in " + Thread.currentThread().getName());
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					int data = new Random().nextInt(100);
					dataMap.put(Thread.currentThread(), data);
					System.out.println(Thread.currentThread().getName()
							+ " has put " + data + " into dataMap");
					new A().getData();
					new B().getData();
					new C().getData();
				}

			}).start();
		}
	}

}
