/**
 * 
 */
/**
 * @author yangkai
 *
 */
package com.training.annotation.validator;

import java.io.Serializable;

import org.junit.Test;
import org.springframework.validation.annotation.Validated;

import com.training.annotaion.validator.aop.NotEmpty;

public class TestValidator {
    
    @Test
    public void test() {
        User user = new User();
        UserService service = new UserService();
        service.show(user);
        
        
    }
    
    public static class UserService {
        void show(@Validated User user){
            System.out.println(user.getName());
        }
    }
    
    public static class User implements Serializable {

        private static final long serialVersionUID = 6040532265028967429L;
        
        @NotEmpty(values = {}, message = "name should not be empty")
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        
        
    }
}