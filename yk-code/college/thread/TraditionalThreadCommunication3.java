package com.yangkai.thread;

public class TraditionalThreadCommunication3 {

	public static void main(String[] args) {
		final Demo d=new Demo();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<50;i++){
					d.sub();
				}
			}
		}).start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<50;i++){
					d.main();
				}
			}
		}).start();
	}

}
class Demo{
	private boolean flag=true;
	public synchronized void main(){
		if(flag){
			for(int i=0;i<100;i++){
				System.out.println("main thread :the loop of "+(i+1));
			}
			flag=false;
			notify();
		}
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public synchronized void sub(){
		if(flag){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int i=0;i<10;i++){
			System.out.println("sub thread :the loop of "+(i+1));
		}
		flag=true;
		notify();
	}
}
