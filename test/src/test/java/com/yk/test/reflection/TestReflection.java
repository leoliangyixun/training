package com.yk.test.reflection;

import java.lang.reflect.Field;

import org.junit.Test;

public class TestReflection {
	@Test
	public void test() throws Exception{
		Message message = new Message();
      /*  for (Field field : message.getClass().getFields()) {
        	System.out.println(field.getName() + " : ");    
          
        }*/
        
        for (Field field : message.getClass().getDeclaredFields()) {
        	
        	
        	System.out.println(field.getName() + " : ");    
          
        } 
        
	}

}
