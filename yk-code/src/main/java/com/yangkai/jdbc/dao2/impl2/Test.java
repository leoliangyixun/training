package org.yangkai.jdbc.dao2.impl2;

import java.util.Date;

import org.yangkai.jdbc.domain.User;

public class Test {

	public static void main(String[] args) {
		UserCallbackImpl userdao=new UserCallbackImpl();
		User user=new User();
		user.setName("��ɯ");
		user.setSex("Ů");
		user.setBirthday(new Date());
		user.setAddr("Ӣ��");
		boolean addSuccess=userdao.addUser2(user);
		System.out.println(addSuccess);
	}

}
