package com.yangkai.test.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.yangkai.test.domain.Person;

public class MyCache {

	public MyCache() {

	}

	public static void main(String[] args) {
		final Cache c = new Cache();
		// c.output();

		new Thread(new Runnable() {

			@Override
			public void run() {
				Person p = (Person) c.get("yangkai");
				System.out.println(p.getName() + " , " + p.getSex() + " , "
						+ p.getAge());
				c.output();
			}

		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				Person p = (Person) c.get("fucui");
				System.out.println(p.getName() + " , " + p.getSex() + " , "
						+ p.getAge());
				c.output();
			}

		}).start();

	}

}

class Cache {
	private Map<String, Object> data = new HashMap<String, Object>();
	private Map<String, Object> cache = new HashMap<String, Object>();
	private ReadWriteLock rwl = new ReentrantReadWriteLock();
	private Lock r = rwl.readLock();
	private Lock w = rwl.writeLock();

	public Cache() {
		init();
	}

	public void init() {
		Person p1 = new Person();
		Person p2 = new Person();
		Person p3 = new Person();
		p1.setName("yangkai");
		p1.setSex('M');
		p1.setAge(25);
		p2.setName("fucui");
		p2.setSex('W');
		p2.setAge(23);
		p3.setName("chenjing");
		p3.setSex('W');
		p3.setAge(23);
		data.put("yangkai", p1);
		data.put("fucui", p2);
		data.put("chenjing", p3);
		// cache.put("yangkai", p1);
	}

	public Object get(String key) {
		Object obj = cache.get(key);
		r.lock();
		if (obj == null) {
			r.unlock();
			w.lock();
			try {
				if (obj == null) {
					obj = query(key);
					if (obj != null) {
						cache.put(key, obj);
					}
				}
			} finally {
				w.unlock();
			}
		}
		return obj;
	}

	public Object query(String key) {
		return data.get(key);
	}

	public void output() {
		// System.out.println(data);
		System.out.println(cache);
	}
}