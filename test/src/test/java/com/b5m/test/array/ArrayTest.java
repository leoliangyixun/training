/**
 * 
 */
package com.b5m.test.array;

import java.io.Serializable;

import org.apache.commons.lang3.ArrayUtils;

public class ArrayTest {


    public ArrayTest() {
        
    }


    public static void main(String[] args) {
        /*
         * String[] a = {"hello", "world", null,null, "jiqingchuan"};
         * System.out.println(a.length); for (String str : a) {
         * System.out.println(str); } System.out.println(a);
         */

        User[] users = new User[] { new User("a", 10), new User("b", 15), null, new User("c", 20) };
        System.out.println(users.length);
        if (ArrayUtils.isNotEmpty(users)) {
            for (User user : users) {
                user.show();
            }
        }

    }
    
    public static class User implements Serializable {

        private static final long serialVersionUID = -2229140190195332249L;
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
        
        public void show() {
            System.out.println("name: " + name + " age: " + age);
        }
    }

}
