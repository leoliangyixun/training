package com.yangkai.sql.dao.impl;

import com.yangkai.sql.domain.User;

public class Test {

	public Test() {
		
	}

	public static void main(String[] args) {
		UserDaoImpl userdao=new UserDaoImpl();
		User user=userdao.findUserById(10);
		if(user!=null){
			System.out.println(user.getUserId());
			System.out.println(user.getUsername());
			System.out.println(user.getUserSex());
			System.out.println(user.getUserBirthday());
			System.out.println(user.getUserAddress());
		}else{
			System.out.println("没有该用户");
		}
	}

}
