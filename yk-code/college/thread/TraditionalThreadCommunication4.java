package com.yangkai.thread;

public class TraditionalThreadCommunication4 {
	public static boolean flag = true;

	public static void main(String[] args) {
		Object obj = new Object();
		new Thread(new Sub(obj)).start();
		new Thread(new Main(obj)).start();
		/**
		 * �ó���ʹ��ͬ���������ʹ�߳�ͬ��
		 * ��ˣ�wait()��notify()��notifyAll()�����ĵ��ñ���ʹ��ͬ��������������
		 * ����ʹ�õ�ǰ����������
		 */
	}
	public static class Sub implements Runnable {
		private Object obj;

		public Sub(Object obj) {
			this.obj = obj;
		}

		@Override
		public void run() {
			for (int i = 0; i < 50; i++) {
				this.output();
			}
		}

		public void output() {
			if (TraditionalThreadCommunication4.flag) {
				try {
					obj.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			synchronized (obj){
				for (int i = 0; i < 10; i++) {
					System.out.println("sub thread :the loop of " + (i + 1));
				}
				TraditionalThreadCommunication4.flag = true;
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
			for (int i = 0; i < 50; i++) {
				this.output();
			}
		}

		public void output() {
			if (TraditionalThreadCommunication4.flag) {
			synchronized (obj){
				for (int i = 0; i < 100; i++) {
					System.out.println("main thread :the loop of " + (i + 1));
				}
				TraditionalThreadCommunication4.flag = false;
				obj.notify();	
				}
			}
			try {
				obj.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

