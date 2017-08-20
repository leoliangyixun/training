package com.yangkai.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

	public ThreadPool() {

	}

	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		for (int i = 1; i <= 5; i++) {
			final int curr = i;
			threadPool.execute(new Runnable() {

				@Override
				public void run() {
					for (int j = 1; j <= 5; j++) {
						System.out.println(Thread.currentThread().getName()
								+ " run the loop of " + j + " in task " + curr);
					}

				}
			});
		}
	}

}
