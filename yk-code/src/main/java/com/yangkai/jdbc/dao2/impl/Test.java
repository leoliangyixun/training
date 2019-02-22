package org.yangkai.jdbc.dao2.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.yangkai.jdbc.domain.Account;
import org.yangkai.jdbc.domain.User;

public class Test {

	public static void main(String[] args) {
		UserAction udao=new UserAction();
		/*
		for(int i=1;i<=10;i++){
			User user=udao.find(i);
			System.out.println("id:"+user.getId()
					+";name:"+user.getName()
					+";sex:"+user.getSex()
					+";birthday:"+new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(user.getBirthday())
					+";addr:"+user.getAddr());
			
//			System.out.println("id:"+user.getId());
//			System.out.println("name:"+user.getName());
//			System.out.println("sex:"+user.getSex());
//			System.out.println("birthday:"+new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(user.getBirthday()));
//			System.out.println("addr:"+user.getAddr());
			
		}
		System.out.println("------------------------");
		*/
		/*
		AccountAction adi=new AccountAction();
		for(int i=1;i<=5;i++){
			Account account=adi.find(i);
			System.out.println("id:"+account.getId()
					+";name:"+account.getName()
					+";sex:"+account.getBalance());	
		}
		System.out.println("------------------------");
		*/
		User user=udao.find(7);
		System.out.println("id:"+user.getId()
				+";name:"+user.getName()
				+";sex:"+user.getSex()
				+";birthday:"+new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(user.getBirthday())
				+";addr:"+user.getAddr());
		System.out.println("------------------------");
		List<User> list=udao.findAll(6);
		if(list==null){
			System.out.println("List<User>ЮЊПе");
		}else{
			for(int i=0;i<list.size();i++){
				System.out.println(list.get(i).toString());
			}
		}
	}

}
