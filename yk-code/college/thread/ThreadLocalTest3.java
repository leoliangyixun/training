package com.yangkai.thread;

import java.util.Random;

public class ThreadLocalTest3 {

	static class A {
		public void getData() {
			System.out.println("A gets " + MyThreadLocal.getPersonInstance()
					+ " in " + Thread.currentThread().getName());
		}
	}

	static class B {
		public void getData() {
			System.out.println("B gets " + MyThreadLocal.getPersonInstance()
					+ " in " + Thread.currentThread().getName());
		}
	}

	static class C {
		public void getData() {
			System.out.println("C gets " + MyThreadLocal.getPersonInstance()
					+ " in " + Thread.currentThread().getName());
		}
	}

	static class D {
		public void getData() {
			System.out.println("D gets " + MyThreadLocal.getAnimalInstance()
					+ " in " + Thread.currentThread().getName());
		}
	}

	static class E {
		public void getData() {
			System.out.println("E gets " + MyThreadLocal.getAnimalInstance()
					+ " in " + Thread.currentThread().getName());
		}
	}

	static class F {
		public void getData() {
			System.out.println("F gets " + MyThreadLocal.getAnimalInstance()
					+ " in " + Thread.currentThread().getName());
		}
	}

	public static void main(String[] args) {

		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {

					int data = new Random().nextInt(10);
					Person p = MyThreadLocal.getPersonInstance();
					p.setName("name" + data);
					p.setAge(data);
					System.out.println(Thread.currentThread().getName()
							+ " has put " + p);
					new A().getData();
					new B().getData();
					new C().getData();
				}

			}).start();
		}

		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {

					int data = new Random().nextInt(10);
					Animal a = MyThreadLocal.getAnimalInstance();
					a.setName("name" + data);
					System.out.println(Thread.currentThread().getName()
							+ " has put " + a);
					new D().getData();
					new E().getData();
					new F().getData();
				}

			}).start();
		}
	}

}

class MyThreadLocal {
	private static ThreadLocal<Person> personThreadLocal = new ThreadLocal<Person>();
	private static ThreadLocal<Animal> animalThreadLocal = new ThreadLocal<Animal>();

	public static Person getPersonInstance() {
		Person p = personThreadLocal.get();
		if (p == null) {
			p = new Person();
			personThreadLocal.set(p);
		}
		return p;
	}

	public static Animal getAnimalInstance() {
		Animal a = animalThreadLocal.get();
		if (a == null) {
			a = new Animal();
			animalThreadLocal.set(a);
		}
		return a;
	}
}

class Person {
	private String name;
	private int age;

	public Person() {

	}

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

class Animal {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
