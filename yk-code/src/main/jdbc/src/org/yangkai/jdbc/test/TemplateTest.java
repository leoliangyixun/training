package org.yangkai.jdbc.test;

import org.yangkai.jdbc.dao2.impl4.CommonTemplate;
import org.yangkai.jdbc.domain.Account;
import org.yangkai.jdbc.domain.User;

public class TemplateTest {

	public static void main(String[] args) 
	{
		String sql1="select id,name ,sex ,birthday,addr from user where id=5";
		String sql2="select id,name ,balance from account where id=2";
		//String sql1="select id as Id,name as Name,sex as Sex,birthday as Birthday,addr as Addr from user where id=1";
		//String sql2="select id as Id,name as Name,balance as Balance from account where id=2";
		User user=new User();
		Account account=new Account();
		//Class<?> clazz1=User.class;
		//Class<?> clazz2=Account.class;
		
		CommonTemplate template=new CommonTemplate();
		User user1=(User) template.find(getClass(user), sql1);
		Account account1=(Account)template.find(getClass(account), sql2);
		System.out.println("id:"+user1.getId());
		System.out.println("name:"+user1.getName());
		System.out.println("sex:"+user1.getSex());
		System.out.println("birthday:"+user1.getBirthday());
		System.out.println("addr:"+user1.getAddr());
		System.out.println("-----------------");
		System.out.println("id:"+account1.getId());
		System.out.println("name:"+account1.getName());
		System.out.println("balance:"+account1.getBalance());

	}
	public static <T> Class<?> getClass(T t)
	{
		return t.getClass();
		
	}

}
