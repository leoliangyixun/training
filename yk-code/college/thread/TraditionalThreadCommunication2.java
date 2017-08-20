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
							 * ������û���漰����������wait()������ʲô�����֣�
							 * ֻ���ڳ���synchronized�ؼ��ֵĵط��Ż��漰������wait()��notify()��notifyAll()������
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
