package com.yangkai.test.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletionServiceTest {

	public CompletionServiceTest() {

	}


	public static void main(String[] args) {
		ExecutorService threadPool=Executors.newFixedThreadPool(3);
		CompletionService<Integer> compService=new ExecutorCompletionService<Integer>(threadPool);
		for(int i=0;i<5;i++){
			final int num=i;
			compService.submit(new Callable<Integer>(){
	
				@Override
				public Integer call() throws Exception {
					Thread.sleep(100);
					return num;
				}
				
			});
		}
		for(int i=0;i<5;i++){
			try {
				System.out.println(compService.take().get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}
