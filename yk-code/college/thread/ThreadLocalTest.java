package com.yangkai.thread;

import java.util.Random;

public class ThreadLocalTest {
	private static ThreadLocal<Integer> threadLocal=new ThreadLocal<Integer>();
	static class A{
		public void getData(){
			System.out.println("A gets "+threadLocal.get()+" in "+Thread.currentThread().getName() );
		}
	}
	static class B{
		public void getData(){
			System.out.println("B gets "+threadLocal.get()+" in "+Thread.currentThread().getName() );
		}
	}
	static class C{
		public void getData(){
			System.out.println("C gets "+threadLocal.get()+" in "+Thread.currentThread().getName() );
		}
	}
	public static void main(String[] args) {
		
		for(int i=0;i<4;i++){
			new Thread(new Runnable(){
	
				@Override
				public void run() {
					int data=new Random().nextInt(10);
					threadLocal.set(data);
					System.out.println(Thread.currentThread().getName()+" has put "+data);
					new A().getData();
					new B().getData();
					new C().getData();
				}
				
			}).start();
		}
	}

}
