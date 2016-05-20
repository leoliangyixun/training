/**
 * 
 */
package com.b5m.training.collection;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.web.servlet.view.tiles2.SpringWildcardServletTilesApplicationContext;

/**
 * @description:
 * Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年5月20日
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年5月20日       jiqingchuan          1.0             Why & What is modified
 */
public class Test {

    /**
     * 
     */
    public Test() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
       Person p1 = new Person("xx", 20, "male");
       Person p2 = new Person("xx", 20, "male");
       System.out.println("---------" + " p1.equals(p2) --->" + p1.equals(p2) + "---------");
       System.out.println("---------" + " p1.hashCode() --->" + p1.hashCode()+ "---------");
       System.out.println("---------" + " p2.hashCode() --->" + p2.hashCode()+ "---------");
       Map<Person, Integer> map = new HashMap<Person, Integer>();
       map.put(p1, 1);
       map.put(p2, 2);
       System.out.println("---------" + map + "---------");
       System.out.println("---------" + map.get(new Person("xx", 20, "male"))+ "---------");
       System.out.println("---------" + map.get(p2)+ "---------");
       
       Set<Person> set = new HashSet<>();
       set.add(p1);
       set.add(p1);
       System.out.println("---------"  + set +" ---------" );
       
       System.out.println("------------------------------------------------------" );
       
       Person p3 = new Person("mm", 20, "female");
       Person p4 = new Person("gg", 20, "male");
       TreeSet<Person> treeSet = new TreeSet<Person>();
       treeSet.add(p3);
       treeSet.add(p4);
       System.out.println("--------------------------" + treeSet + "----------------------------" );
       Collections
       
    }
    
    private static class Person {
        private String name;
        private Integer age;
        private String sex;
        public Person(String name, Integer age, String sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

      @Override
        public int hashCode() {
           final int prime = 31;
            int result = 1;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            result = prime * result + ((sex == null) ? 0 : sex.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof Person)) {
                return false;
            }
            Person other = (Person) obj;

            return this.name.equals(other.name) && this.sex.equals(other.sex) ;
        }

        @Override
        public String toString() {
            return "Person [name=" + name + ", age=" + age + ", sex=" + sex + "]";
        }

    }
    
    public static class User  implements Comparable<Employee> {

        @Override
        public int compareTo(Employee o) {
          
            return 0;
        }
        
    }
    public static class Employee implements Comparator<Employee> {

        @Override
        public int compare(Employee o1, Employee o2) {
            // TODO Auto-generated method stub
            return 0;
        }
        
    }

}
