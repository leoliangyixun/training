/**
 * 
 */
package com.training.spring.test;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

public class User {


    public User() {
       System.out.println("constructor");
    }
    
    //@PostConstruct  
    public void  init(){  
        System.out.println("I'm  init  method  using  @PostConstrut....");  
    }  
      
   // @PreDestroy  
    public void  dostory(){  
        System.out.println("I'm  destory method  using  @PreDestroy.....");  
    } 


    public static void main(String[] args) {
       User u = new User();

    }

}
