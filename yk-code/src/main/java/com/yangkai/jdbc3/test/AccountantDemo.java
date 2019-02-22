package com.yangkai.jdbc.test;

import com.yangkai.jdbc.dao.impl.CommonDAOImpl;
import com.yangkai.jdbc.domain.Accountant;

public class AccountantDemo {

	public static void main(String[] args) 
	{
		CommonDAOImpl dao=new CommonDAOImpl(); 
		Accountant accountant=new Accountant();
		accountant.setName("wangwu");
		accountant.setAccount("0001");
		accountant.setMoney(50000f);
		int id=dao.insertAccountant(accountant);
		System.out.println(id);

	}

}
