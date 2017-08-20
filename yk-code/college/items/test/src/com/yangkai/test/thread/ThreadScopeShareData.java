package com.yangkai.test.thread;

import java.util.Random;

public class ThreadScopeShareData {
	private static int data = 0;

	public ThreadScopeShareData() {

	}

	public static class ModelA {
		public int getData() {
			return data;
		}
	}

	public static class ModelB {
		public int getData() {
			return data;
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					data = new Random().nextInt(100);
					System.out.println(Thread.currentThread().getName()
							+ " has put data: " + data);
					System.out.println("ModelA get data "
							+ new ModelA().getData() + " from "
							+ Thread.currentThread().getName());
					System.out.println("ModelB get data "
							+ new ModelB().getData() + " from "
							+ Thread.currentThread().getName());
				}

			}).start();
		}
	}

}
