package org.yangkai.jdbc.test;

import java.util.Date;

import org.yangkai.jdbc.dao2.impl4.DaoImpl2;
import org.yangkai.jdbc.domain.Account;
import org.yangkai.jdbc.domain.User;
import org.yangkai.jdbc.service.Service;
import org.yangkai.jdbc.service.impl.ServiceImpl;

public class DaoDemo {
	
	public static void main(String[] args) {
		Service s=new ServiceImpl();
		DaoImpl2 dao=new DaoImpl2();
		User user1=s.findUserById(User.class, 7);
		Account account1=s.findAccountById(Account.class, 4);
		User user2=s.findUserByName(User.class, "'����'");
		Account account2=s.findAccountByName(Account.class, "'����'");
		System.out.println("id:"+user1.getId());
		System.out.println("name:"+user1.getName());
		System.out.println("sex:"+user1.getSex());
		System.out.println("birthday:"+user1.getBirthday());
		System.out.println("addr:"+user1.getAddr());
		System.out.println("-----------------------------");
		System.out.println("id:"+user2.getId());
		System.out.println("name:"+user2.getName());
		System.out.println("sex:"+user2.getSex());
		System.out.println("birthday:"+user2.getBirthday());
		System.out.println("addr:"+user2.getAddr());
		System.out.println("-----------------------------");
		System.out.println("id:"+account1.getId());
		System.out.println("name:"+account1.getName());
		System.out.println("balance:"+account1.getBalance());
		System.out.println("-----------------------------");
		System.out.println("id:"+account2.getId());
		System.out.println("name:"+account2.getName());
		System.out.println("balance:"+account2.getBalance());
		
		User user3=new User(); 
		user3.setName("���");
		user3.setSex("Ů");
		user3.setBirthday(new Date());
		user3.setAddr("����");
		Account account3=new Account();
		account3.setName("����");
		account3.setBalance(2000.0f);
		/*
		boolean saveUser=s.saveUser(user3);
		boolean saveAccount=s.saveAccount(account3);
	    if(saveUser==true)
		{
			System.out.println("���user�ɹ�");
		}else{
			System.out.println("���userʧ��");
		}
		if(saveAccount==true)
		{
			System.out.println("���account�ɹ�");
		}else{
			System.out.println("���accountʧ��");
	    }
	    */	
	}
}
