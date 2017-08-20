package com.yangkai.thread;

public class ThreadSafety2 {
	class Outputer{
		public synchronized void print(String name){
			for(int i=0;i<name.length();i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		public void output(String name){
			for(int i=0;i<name.length();i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
	}
	public void init(){
		new Outputer().output("fuck you");
	}
	public void test(){
		
		//final Outputer outputer=this.new Outputer();//可以。
		final Outputer outputer=new Outputer();//可以。
		//final Outputer outputer=null;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
			
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//new ThreadSafety().new Outputer().output("yangkai");
					outputer.output("yangkai");
				}
			}
		}).start();
		
	}
	public static void main(String[] args) {
		ThreadSafety2 t2=new ThreadSafety2();
		t2.test();
	}

}

