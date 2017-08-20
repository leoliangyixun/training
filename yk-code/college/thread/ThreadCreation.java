package com.yangkai.thread;

public class ThreadCreation {

	public static void main(String[] args) {
		Thread t1=new Thread();
		t1.start();
		new Thread(){
			public void run() {
				while(true){
					System.out.println("fuck you");
				}
			};
		}.start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		}).start();
		
	}

}
