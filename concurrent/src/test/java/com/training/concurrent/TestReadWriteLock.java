package com.training.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.junit.Test;

/**
 * Created by izene on 2016/6/12.
 */
public class TestReadWriteLock {
    
    final static ReadWriteLock rwl = new ReentrantReadWriteLock(false);
    final static Lock readLock = rwl.readLock();
    final static Lock writeLock = rwl.writeLock();
    final static Resource resource = new Resource();
    
    @Test
    public void test() {
       new Thread(new Writer(1)).start();
       new Thread(new Reader()).start();
       new Thread(new Reader()).start();
       new Thread(new Writer(2)).start();
       new Thread(new Writer(3)).start();
       new Thread(new Reader()).start();
       new Thread(new Writer(4)).start();
       new Thread(new Reader()).start();
       new Thread(new Writer(5)).start();
       new Thread(new Reader()).start();

        
        // 保证前面的线程都执行完
        while (Thread.activeCount() > 1) { 
            Thread.yield();
        }
    }
    
    public static class Resource {
        private Integer count;

        public void set(int count) {
            this.count = count;
        }
        
        public int get() {
            return this.count;
        }
    }
    
    public static class Reader implements Runnable {
        
        @Override
        public void run() {
            try {
                readLock.lock();
                System.out.println(Thread.currentThread().getName() + " begin to get count......");
                int count = resource.get();
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + " get count " + count);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }

            
        }
    
    }
    public static class Writer implements Runnable {
        private int count;
        
        
        
        public Writer(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            try {
                writeLock.lock();
                System.out.println(Thread.currentThread().getName() + " begin to set count......");
                resource.set(count);
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + " set count to " + count);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }

            
        }
    
    }
    
    @Test
    public void test2() {
        int a = 2;
        int b = a;
        System.out.println(++a);
        System.out.println(b);
    }
    
    @Test
    public void test3() {
        
        Integer m = new Integer(2000);
        Integer n = m;
        System.out.println(++m);
        System.out.println(n);
    }
    
    @Test
    public void test4() {
        
        String s1 = new String("hello");
        String s2 = s1;
        s1 = s1 + "world";
        System.out.println(s1);
        System.out.println(s2);


        StringBuilder sb1 = new StringBuilder("welcome");
        StringBuilder sb2 =sb1;
        sb1.append(" beijing");
        System.out.println(sb1);
        System.out.println(sb2);
    }
    
    @Test
    public void test5() {
        User u1 = new User("jiqingchuan", 28);
        User u2 = u1;
        u1.setName("kerwin yang");
        u1.setAge(27);
        System.out.println(u1);
        System.out.println(u2);
    }
    
    public static class User {
        private String name;
        private Integer age;
        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Integer getAge() {
            return age;
        }
        public void setAge(Integer age) {
            this.age = age;
        }
        
        @Override
        public String toString() {
          
            return "[name=" + this.name +"; age = " + this.age + "]";
        }
        
        
    }

}
