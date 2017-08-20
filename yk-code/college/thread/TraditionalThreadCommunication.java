package com.yangkai.thread;

public class TraditionalThreadCommunication {
	public static boolean flag = true;

	public static void main(String[] args) {
		Object obj = new Object();
		new Thread(new Main(obj)).start();
		new Thread(new Sub(obj)).start();
		/**
		 * 该程序使用同步代码块来使线程同步
		 * 因此，wait()、notify()、notifyAll()方法的调用必须使用同步监视器来调用
		 * 不能使用当前对象来调用
		 */
	}
	public static class Sub implements Runnable {
		private Object obj;

		public Sub(Object obj) {
			this.obj = obj;
		}

		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				this.output(i);
			}
		}

		public void output(int i) {
			synchronized (obj){
				if (TraditionalThreadCommunication.flag) {
					try {
						obj.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (int j = 0; j < 10; j++) {
					System.out.println("Sub : inner loop of " + (j + 1)
							+ " in outer loop of " + (i + 1));
				}
				TraditionalThreadCommunication.flag = true;
				obj.notify();
			}
		}
	}

	public static class Main implements Runnable {
		private Object obj;
		public Main(Object obj) {
			this.obj = obj;
		}

		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				this.output(i);
			}
		}

		public void output(int i) {
			synchronized (obj){
				if (TraditionalThreadCommunication.flag) {
					for (int j = 0; j < 20; j++) {
						System.out.println("Main : inner loop of " + (j + 1)
								+ " in outer loop of " + (i + 1));
					}
				TraditionalThreadCommunication.flag = false;
				obj.notify();	
				}
				try {
					obj.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

