package org.yangkai.jdbc.test;

import org.yangkai.jdbc.dao.DAO;
import org.yangkai.jdbc.dao2.impl4.DaoImpl;
import org.yangkai.jdbc.domain.Account;
import org.yangkai.jdbc.domain.User;

public class Demo {

	public static void main(String[] args) throws Exception {
		DAO dAO=new DaoImpl();
		//Class<User> clazz1=User.class;
		//Class<Account> clazz2=Account.class;
		Class clazz1=User.class;
		Class clazz2=Account.class;
		String sql1="select id as Id,name as Name,sex as Sex,birthday as Birthday,addr as Addr from user where id=1";
		String sql2="select id as Id,name as Name,balance as Balance from account where id=2";
		//String sql1="select name as Name,sex as Sex,birthday as Birthday,addr as Addr from user where id=1";
		//String sql2="select name as Name,balance as Balance from account where id=2";
		User user=(User) dAO.find(clazz1,sql1);
		//Account account=(Account) dao.find(clazz2,sql2);
		System.out.println("Demo:"+user.getName());
		//System.out.println("Demo:"+account.getName());
	}

}
