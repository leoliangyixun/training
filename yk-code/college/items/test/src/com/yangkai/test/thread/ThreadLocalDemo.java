package com.yangkai.test.thread;

import java.util.Random;

import com.yangkai.test.domain.Person;

public class ThreadLocalDemo {

	public ThreadLocalDemo() {

	}

	public class A {
		public void getData() {
			System.out.println("A gets "
					+ MyThreadLocal.getMyThreadLocalInstance().getName() + ","
					+ MyThreadLocal.getMyThreadLocalInstance().getAge()
					+ " in " + Thread.currentThread().getName());
		}
	}

	public class B {
		public void getData() {
			System.out.println("B gets "
					+ MyThreadLocal.getMyThreadLocalInstance().getName() + ","
					+ MyThreadLocal.getMyThreadLocalInstance().getAge()
					+ " in " + Thread.currentThread().getName());
		}
	}

	public void run() {
		for (int i = 0; i < 4; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					int data = new Random().nextInt(100);
					Person p = MyThreadLocal.getMyThreadLocalInstance();
					p.setName("name" + data);
					p.setAge(data);
					System.out.println(Thread.currentThread().getName()
							+ " has put name" + data + "," + data);
					new A().getData();
					new B().getData();
				}
			}).start();
		}
	}

	public static void main(String[] args) {
		new ThreadLocalDemo().run();

	}

}

class MyThreadLocal {
	private static ThreadLocal<Person> threadLocal = new ThreadLocal<Person>();

	public static Person getMyThreadLocalInstance() {
		Person p = threadLocal.get();
		if (p == null) {
			p = new Person();
			threadLocal.set(p);
		}
		return p;
	}
}
