package org.yangkai.jdbc.dao.impl;

import java.util.Date;

import org.yangkai.jdbc.dao.AccountDAO;
import org.yangkai.jdbc.dao.UserDAO;
import org.yangkai.jdbc.domain.Account;
import org.yangkai.jdbc.domain.User;

public class SaveTest {

	public static void main(String[] args) 
	{
		UserDAO dao1=new UserDAOInheritanceImpl();
		User user=new User();
		user.setName("angelababy");
		user.setSex("Å®");
		user.setBirthday(new Date());
		user.setAddr("Ïã¸Û");
		boolean bool1=dao1.addUser(user);
		System.out.println(bool1);
		AccountDAO dao2=new AccountDAOInheritanceImpl();
		Account account=new Account();
		account.setName("ÓñÊþ");
		account.setBalance(10000.0f);
		boolean bool2=dao2.addAccount(account);
		System.out.println(bool2);
	}

}
