package org.yangkai.jdbc.dao2.impl;

import org.yangkai.jdbc.domain.Account;
import org.yangkai.jdbc.domain.User;

public class Test2 {
	
	public static void main(String[] args) {
		UserInAction userdao=new UserInAction();
		User user1=userdao.getUser("¥‰¥‰");
		//System.out.println(user1);
		System.out.println("id:"+user1.getId());
		System.out.println("name:"+user1.getName());
		System.out.println("sex:"+user1.getSex());
		System.out.println("birthday:"+user1.getBirthday());
		System.out.println("addr:"+user1.getAddr());
		System.out.println("-----------------------------");
		/*
		User user2=userdao.getUser(1);
		System.out.println("id:"+user2.getId());
		System.out.println("name:"+user2.getName());
		System.out.println("sex:"+user2.getSex());
		System.out.println("birthday:"+user2.getBirthday());
		System.out.println("addr:"+user2.getAddr());
		System.out.println("----------------------------");
		*/
		
		AccountInAction accountdao=new AccountInAction();
		Account account1 =accountdao.getAccount(1);
		System.out.println("id:"+account1.getId());
		System.out.println("name:"+account1.getName());
		System.out.println("sex:"+account1.getBalance());
		System.out.println("-----------------------------");
		/*
		Account account2 =accountdao.getAccount("–°¿Ú");
		System.out.println("id:"+account2.getId());
		System.out.println("name:"+account2.getName());
		System.out.println("sex:"+account2.getBalance());
		System.out.println("-----------------------------");
		*/
	}

}
