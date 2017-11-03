package com.yangkai.dao.impl2;

import com.yangkai.dao.vo.User;

public class Test {

	public static void main(String[] args) {
		UserDaoImpl userdao=new UserDaoImpl();
		User user=userdao.findUser(20);
		System.out.println("id:"+user.getId());
		System.out.println("name:"+user.getName());
		System.out.println("sex:"+user.getSex());
		System.out.println("birthday:"+user.getBirthday());
		System.out.println("addr:"+user.getAddr());
		System.out.println("-----------------------------");
	}

}
