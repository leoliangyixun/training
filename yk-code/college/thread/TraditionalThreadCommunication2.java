package com.yangkai.thread;

public class TraditionalThreadCommunication2 {

	public static void main(String[] args) {
		final Test t=new Test();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<50;i++){
					if(t.flag){
						t.main();
						t.flag=false;
						notify();
					}
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<50;i++){
					if(t.flag){
						try {
							/**
							 * 根本就没有涉及到锁，调用wait()方法有什么意义咧？
							 * 只有在出现synchronized关键字的地方才会涉及到调用wait()、notify()、notifyAll()方法。
							 */
							wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					t.sub();
					t.flag=true;
					notify();
				}
			}
		}).start();
	}

}
class Test{
	public boolean flag=true;
	public synchronized void main(){
		for(int i=0;i<100;i++){
			System.out.println("main thread :the loop of "+(i+1));
		}
	}
	public synchronized void sub(){
		for(int i=0;i<10;i++){
			System.out.println("sub thread :the loop of "+(i+1));
		}
	}
}
