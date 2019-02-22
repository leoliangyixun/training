package org.yangkai.jdbc.test;
import java.util.Date;

import org.yangkai.jdbc.dao.impl.CommonDaoImpl;
import org.yangkai.jdbc.dao.impl.UserDaoImpl;
import org.yangkai.jdbc.dao.impl.UserDaoImpl2;
import org.yangkai.jdbc.domain.User;

public class UserDemo {

	public UserDemo() {
		
	}


	public static void main(String[] args) 
	{
		/*
		CommonDaoImpl dao=new CommonDaoImpl(); 
		User user=new User();
		user.setName("lisi");
		user.setJob(".NETπ§≥Ã ¶");
		user.setHiredate(new Date());
		user.setSalary(5000f);
		int id=dao.insertUser(user);
		System.out.println(id);
		*/
		
		UserDaoImpl dao1=new UserDaoImpl(); 
		User u1=new User();
		u1.setId(1);
		User u2=dao1.findUser(u1);
		
		System.out.println("--------------");
		UserDaoImpl2 dao2=new UserDaoImpl2(); 
		User u3=new User();
		u3.setId(1);
		User u4=dao2.findUserName(u3);
		System.out.println(u4.getName());
	}
}
