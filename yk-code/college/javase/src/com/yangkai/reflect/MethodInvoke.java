package com.yangkai.reflect;

import java.lang.reflect.Method;

import com.yangkai.vo.User;

public class MethodInvoke {

	public MethodInvoke() {
		
	}

	public static void main(String[] args) throws Exception {
		Class<?> clazz=User.class;
		Object obj=clazz.newInstance();
		/*
		System.out.println(obj instanceof User);
		Method m1=clazz.getMethod("show", String.class);
	    m1.invoke(obj, "Ñî¿ª");
	    Method m2=clazz.getMethod("show", String.class,Integer.class);
	    User user=(User) m2.invoke(obj, "ö¦ö¦",1);
		System.out.println(user.getName());
		System.out.println(user.getId());
		*/
		Method[] ms=clazz.getMethods();
		for(Method m:ms){
			if(m.getName().equals("show")){
				Object result=m.invoke(obj, "´ä´ä",1);
				System.out.println(result);
				//System.out.println(user.getName());
				//System.out.println(user.getId());
			}
		}
		

	}

}
