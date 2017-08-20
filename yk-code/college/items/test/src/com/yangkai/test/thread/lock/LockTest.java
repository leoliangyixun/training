package com.yangkai.test.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.yangkai.test.thread.lock.MyLockDemo.Outputer;
import com.yangkai.test.thread.lock.MyLockDemo.Printer;

public class LockTest {

	public LockTest() {
		
	}


	public static void main(String[] args) {
		MyLockDemo myLock=new MyLockDemo();
//		final Outputer outputer=myLock.new Outputer();
		/*
		 * No enclosing instance of type MyLockDemo is accessible. 
		 * Must qualify the allocation with an enclosing instance of type MyLockDemo 
		 * (e.g. x.new A() where x is an instance of MyLockDemo).
		 * */
//		final Outputer outputer=new Outputer();
		final Printer printer=new Printer();
		new Thread(new Runnable(){

			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
//					outputer.output("yangkai");
					printer.print("yangkai");
				}
			}
			
		}).start();
		new Thread(new Runnable(){

			@Override
			public void run() {
				while(true){
//					outputer.output("fucui");
					printer.print("fucui");
				}
			}
			
		}).start();

	}

}
class MyLockDemo{
	public static class Printer{
		private final Lock lock=new ReentrantLock();
		public void print(String name){
			lock.lock();
			try{
				if(name!=null && name.length()>0){
					for(int i=0;i<name.length();i++){
						System.out.print(name.charAt(i));
					}
					System.out.println();
				}
			}finally{
				lock.unlock();
			}
		}
	}
	public class Outputer{
		public void output(String name){
			if(name!=null && name.length()>0){
				for(int i=0;i<name.length();i++){
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}
		}
	}
	
}
