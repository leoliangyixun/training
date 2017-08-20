package com.yangkai.test.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTest {

	public CallableTest() {
		
	}


	public static void main(String[] args) {
		ExecutorService threadPool=Executors.newFixedThreadPool(3);
		Future<String> future=threadPool.submit(new Callable<String>(){

			@Override
			public String call() throws Exception {
				Thread.sleep(1000);
				return "fuck";
			}
			
		});
		try {
			String result=future.get();
			System.out.println(result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

}
