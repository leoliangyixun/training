package com.yangkai.thread;

import java.util.Random;

public class ThreadLocalTest2 {
	private static ThreadLocal<User> threadLocal = new ThreadLocal<User>();

	static class A {
		public void getData() {
			System.out.println("A gets " + threadLocal.get() + " : "
					+ threadLocal.get().getName() + ";"
					+ threadLocal.get().getAge() + " in "
					+ Thread.currentThread().getName());
		}
	}

	static class B {
		public void getData() {
			System.out.println("B gets " + threadLocal.get() + " : "
					+ threadLocal.get().getName() + threadLocal.get().getAge()
					+ " in " + Thread.currentThread().getName());
		}
	}

	static class C {
		public void getData() {
			System.out.println("C gets " + threadLocal.get() + " : "
					+ threadLocal.get().getName() + threadLocal.get().getAge()
					+ " in " + Thread.currentThread().getName());
		}
	}

	public static void main(String[] args) {

		for (int i = 0; i < 4; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					User user = new User();
					int data = new Random().nextInt(10);
					user.setName("name" + data);
					user.setAge(data);
					threadLocal.set(user);
					System.out.println(Thread.currentThread().getName()
							+ " has put " + user);
					new A().getData();
					new B().getData();
					new C().getData();
				}

			}).start();
		}
	}

}

class User {
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
