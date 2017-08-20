package com.yangkai.test.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedualedThreadPool {

	public SchedualedThreadPool() {
		
	}

	public static void main(String[] args) {
		ScheduledExecutorService schedualedThreadPool=Executors.newScheduledThreadPool(3);
		for(int i=1;i<=5;i++){
			schedualedThreadPool.schedule(new Runnable(){
	
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+" running.");
					
				}
				
			}, 
			1, 
			TimeUnit.SECONDS);
		}
	}

}
