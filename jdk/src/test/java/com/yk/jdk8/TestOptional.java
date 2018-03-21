/**
 * 
 */
package com.yk.jdk8;

import java.util.Optional;

import org.junit.Test;

/**
 * @author yangkai
 *
 */
public class TestOptional {
    
    @Test
    public void testOptional() {
        Person p = new Person();
     
    }
    
    public static class Person {
        private Optional<Car> car;

        public Optional<Car> getCar() {
            return car;
        }

        public void setCar(Optional<Car> car) {
            this.car = car;
        }
        
        
    }
    
    public static class Car {
        
    }

}
