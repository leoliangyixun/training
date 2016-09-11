package com.b5m.test.collection;

import org.junit.Test;

/**
 * Created by izene on 2016/6/15.
 */
public class TestStack {
    
    @Test
    public void test () {
        Stack<User> s = new Stack<User>(3);
        User u1 = new User("gg", "male", 28);
        User u2 = new User("mm", "female", 20);
        s.push(u1);
        s.push(u2);
        System.out.println(s);
        System.out.println(s.count);
        User u = s.pop();
        System.out.println(u);
        
    }
    
    public static class Stack<E> {
        private E[] elements;
        private int count;
        
        @SuppressWarnings("unchecked")
        public Stack (int capacity) {
            
            //不能实例化不可具体化的类型实例，如泛型E
            //elements = new E[capacity];
            elements = (E[])new Object[capacity];
        }
        
        public void push(E e) {
            elements[count++] = e;
        }
        
        public E pop() {
            int len = count;
            return elements[len-1];
        }
        
        
    }
    public static class User {
        private String name;
        private String gender;
        private int age;
        
        public User(String name, String gender, int age) {
            super();
            this.name = name;
            this.gender = gender;
            this.age = age;
        }
        @Override
        public String toString() {
           
            return "[name = "+this.name+"; gender = "+this.gender+"; age = "+this.age+"]";
        }
        
 
        
    }

}
