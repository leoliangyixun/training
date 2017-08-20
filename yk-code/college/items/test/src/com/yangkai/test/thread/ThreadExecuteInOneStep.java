package com.yangkai.test.thread;

public class ThreadExecuteInOneStep {
	private static Integer a=0;//可以作为同步监视器。
	private static int b=0;//不能作为同步监视器。
	public ThreadExecuteInOneStep() {

	}

	public static void main(String[] args) {
		final Object obj=new Object();
		for(int i=0;i<6;i++){
			new Thread(new Runnable(){

				@Override
				public void run() {
					synchronized(a){
						System.out.println(Thread.currentThread().getName()+": start");
						System.out.println(Thread.currentThread().getName()+": running");
						System.out.println(Thread.currentThread().getName()+": end");
					}
					synchronized(obj){
						System.out.println(Thread.currentThread().getName()+": start");
						System.out.println(Thread.currentThread().getName()+": running");
						System.out.println(Thread.currentThread().getName()+": end");
					}
				}
				
			}).start();
		}
//		System.out.println("Main thread end.");
	}

}
