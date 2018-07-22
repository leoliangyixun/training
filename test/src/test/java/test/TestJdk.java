/**
 * 
 */
package test;

import java.util.Arrays;
import java.util.Date;

import org.junit.Test;

import com.hujiang.basic.framework.core.util.JsonUtil;

import lombok.Data;

/**
 * @author yangkai
 *
 */
public class TestJdk {
    
    @Test
    public void test() {
        Person[] persons = new Person[] {new Person(20), new Person(18), new Person(30), new Person(15)};
        System.out.println("before sort: " + JsonUtil.object2JSON(persons));
        Arrays.sort(persons, Person::compareByAge);
        System.out.println("after sort: " + JsonUtil.object2JSON(persons));
    }

    @Data
    public static class Person {
        int age;
        Date birthday;

        public Person(int age) {
            this.age = age;
        }

//    public static int compareByAge(Person obj) {
//        return -1;
//    }

        public int compareByAge(Person obj) {
            return this.age > obj.getAge() ? 1 : this.age == obj.getAge() ? 0 : -1;
        }
//
//        public int compareByAge(Person obj1, Person obj2) {
//            return obj1.getAge() > obj2.getAge() ? 1 : obj1.getAge() == obj2.getAge() ? 0 : -1;
//        }

//        public static int compareByAge(Person obj1, Person obj2) {
//            return obj1.getAge() > obj2.getAge() ? 1 : obj1.getAge() == obj2.getAge() ? 0 : -1;
//        }

        @Override
        public String toString() {
            return JsonUtil.object2JSON(this);
        }
    }

    public static class PersonComparator {
        public int compareByAge(Person obj1, Person obj2) {
            return obj1.getAge() > obj2.getAge() ? 1 : obj1.getAge() == obj2.getAge() ? 0 : -1;
        }
    }
}


