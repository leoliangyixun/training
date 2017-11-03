package org.yangkai.jdbc.dao.impl;

import org.yangkai.jdbc.dao.AccountDAO;
import org.yangkai.jdbc.dao.UserDAO;
import org.yangkai.jdbc.domain.Account;
import org.yangkai.jdbc.domain.User;

public class Test {

	public static void main(String[] args) 
	{
		UserDAO dao1=new UserDAOInheritanceImpl();
		
		User user1=dao1.getUser(6);
		System.out.println("id:"+user1.getId());
		System.out.println("name:"+user1.getName());
		System.out.println("sex:"+user1.getSex());
		System.out.println("birthday:"+user1.getBirthday());
		System.out.println("addr:"+user1.getAddr());
		System.out.println("----------------------------");
		
		User user2=dao1.getUser("´ä´ä");
		System.out.println("id:"+user2.getId());
		System.out.println("name:"+user2.getName());
		System.out.println("sex:"+user2.getSex());
		System.out.println("birthday:"+user2.getBirthday());
		System.out.println("addr:"+user2.getAddr());
		System.out.println("----------------------------");
		/*
		int count=dao.removeUser(3);
		if(count>0)
		{
			System.out.println("É¾³ýuser³É¹¦");
		}else{
			System.out.println("É¾³ýuserÊ§°Ü");
		}
		*/
		AccountDAO dao2=new AccountDAOInheritanceImpl();
		
		Account account=dao2.getAccount(3);
		System.out.println("id:"+account.getId());
		System.out.println("name:"+account.getName());
		System.out.println("sex:"+account.getBalance());
		System.out.println("----------------------------");
		
		Account account2=dao2.getAccount("ö¦ö¦");
		System.out.println("id:"+account2.getId());
		System.out.println("name:"+account2.getName());
		System.out.println("sex:"+account2.getBalance());
		System.out.println("----------------------------");
	}
}
