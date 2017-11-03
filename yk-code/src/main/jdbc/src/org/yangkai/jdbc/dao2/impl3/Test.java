package org.yangkai.jdbc.dao2.impl3;

import java.util.List;

import org.yangkai.jdbc.domain.User;

public class Test {

	public static void main(String[] args) {
		
		UserInstanceCallback userdao2=new UserInstanceCallback();
		for(int i=0;i<100;i++){
			User user2=userdao2.find(10);
			System.out.println(user2+"--->"+i);
		}
		
		/*
		for(int i=0;i<10;i++){
			UserStaticCallback userdao=new UserStaticCallback();
			UserCallbackImpl userdao2=new UserCallbackImpl();
			UserInstanceCallback2 userdao3=new UserInstanceCallback2();
			User user=userdao.find(10);
			User user2=userdao2.find(10);
			User user3=userdao3.find(10);
			System.out.println(user);
			System.out.println(user2);
			System.out.println(user3);
		}
		*/
		/*
		for(int i=0;i<10;i++){
			
			UserStaticCallback userdao=new UserStaticCallback();
			UserCallbackImpl userdao2=new UserCallbackImpl();
			UserInstanceCallback2 userdao3=new UserInstanceCallback2();
			List<User> users2=userdao2.findAll(10);
			List<User> users=userdao.findAll(10);
			
			List<User> users3=userdao3.findAll(10);
			System.out.println(users);
			System.out.println(users2);
			System.out.println(users3);
		}
		*/
		/*
		UserStaticCallback userdao=new UserStaticCallback();
		UserCallbackImpl userdao2=new UserCallbackImpl();
		UserInstanceCallback2 userdao3=new UserInstanceCallback2();
		User user=userdao.find(9);
		User user2=userdao2.find(9);
		User user3=userdao3.find(9);
		System.out.println(user);
		System.out.println(user2);
		System.out.println(user3);
		*/

	}

}
