package org.yangkai.jdbc.test;

import org.yangkai.jdbc.dao.impl.CommonDaoImpl;
import org.yangkai.jdbc.domain.Account;

public class AccountDemo {

	public static void main(String[] args) 
	{
		CommonDaoImpl dao=new CommonDaoImpl(); 
		Account account=new Account();
		account.setName("wangwu");
		account.setBalance(50000f);
		int id=dao.insertAccount(account);
		System.out.println(id);

	}

}
