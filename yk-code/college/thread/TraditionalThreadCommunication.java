package com.yangkai.thread;

public class TraditionalThreadCommunication {
	public static boolean flag = true;

	public static void main(String[] args) {
		Object obj = new Object();
		new Thread(new Main(obj)).start();
		new Thread(new Sub(obj)).start();
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

