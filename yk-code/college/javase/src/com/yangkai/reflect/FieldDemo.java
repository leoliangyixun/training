package com.yangkai.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.yangkai.vo.Student;
import com.yangkai.vo.User;

public class FieldDemo {
	public Class<?> classType;
	public FieldDemo() {
	}
	public void objectCopy(Object obj){
		this.classType=obj.getClass();
		Field [] fields=this.classType.getDeclaredFields();
		for(Field field:fields){
			String getMethodName="get"+field.getName().substring(0, 1).toUpperCase()+field.getName().substring(1);
			try {
				Method method=this.classType.getDeclaredMethod(getMethodName, new Class<?>[]{});
				Object result=method.invoke(obj, new Object[]{});
				System.out.println(result);
				
				//System.out.println(getMethodName);
				//System.out.println(field);
				//System.out.println(field.getName());
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) {
		Student s=new Student();
		s.setName("Ñî¿ª");
		User u=new User();
		u.setName("yangkai");
		FieldDemo fieldDemo=new FieldDemo();
		fieldDemo.objectCopy(s);
		System.out.println("---------------------");
		fieldDemo.objectCopy(u);

	}

}
