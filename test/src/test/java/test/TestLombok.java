package test;

import com.hujiang.basic.framework.rest.validation.annotation.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import org.junit.Test;

/**
 * @author yangkai
 * @date 2021/1/13
 * @email yangkai@hujiang.com
 * @description
 */
public class TestLombok {

    @Test
    public  void test1() {
        Student student = Student.builder().name("xxx").age(5).grade("3").build();
    }

    @SuperBuilder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static abstract class Person {
        private String name;
        private int age;
        private String  email;
    }

    @SuperBuilder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static  class Student  extends Person {
        private String grade;
    }

}
