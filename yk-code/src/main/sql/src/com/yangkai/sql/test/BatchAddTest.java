package com.yangkai.sql.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yangkai.sql.domain.User;

public class BatchAddTest {

	public static void main(String[] args) {
		BatchAdd add=new BatchAdd();
		User user1=new User();
		user1.setUsername("�");
		user1.setUserSex("��");
		user1.setUserBirthday(new Date());
		user1.setUserAddress("�人");
		
		User user2=new User();
		user2.setUsername("�");
		user2.setUserSex("��");
		user2.setUserBirthday(new Date());
		user2.setUserAddress("�人");
		
		User user3=new User();
		user3.setUsername("�");
		user3.setUserSex("��");
		user3.setUserBirthday(new Date());
		user3.setUserAddress("�人");
		
		User user4=new User();
		user4.setUsername("�");
		user4.setUserSex("��");
		user4.setUserBirthday(new Date());
		user4.setUserAddress("�人");
		
		List<User> users=new ArrayList<User>();
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		
		int[] counts=add.addUsers(users);
		if(counts!=null && counts.length>0){
			for(int count:counts){
			System.out.println(count);
			}
		}
	}

}
