package com.yangkai.jdbc.test;
import java.util.Date;

import com.yangkai.jdbc.dao.impl.CommonDAOImpl;
import com.yangkai.jdbc.dao.impl.UserDAOImpl;
import com.yangkai.jdbc.dao.impl.UserDAOImpl2;
import com.yangkai.jdbc.domain.User;

public class UserDemo {

	public UserDemo() {
		
	}


	public static void main(String[] args) 
	{
		/*
		CommonDAOImpl dao=new CommonDAOImpl(); 
		User user=new User();
		user.setName("lisi");
		user.setJob(".NETπ§≥Ã ¶");
		user.setHiredate(new Date());
		user.setSalary(5000f);
		int id=dao.insertUser(user);
		System.out.println(id);
		*/
		
		UserDAOImpl dao1=new UserDAOImpl(); 
		User u1=new User();
		u1.setId(1);
		User u2=dao1.findUser(u1);
		System.out.println(u2.getName());
		System.out.println(u2.getJob());
		System.out.println(u2.getHiredate());
		System.out.println(u2.getSalary());
		System.out.println("--------------");
		UserDAOImpl2 dao2=new UserDAOImpl2(); 
		User u3=new User();
		u3.setId(1);
		User u4=dao2.findUserName(u3);
		System.out.println(u4.getName());
	}
}
