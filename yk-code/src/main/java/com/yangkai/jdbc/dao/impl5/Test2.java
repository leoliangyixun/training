package org.yangkai.jdbc.dao.impl5;

import org.yangkai.jdbc.dao2.AccountDao;
import org.yangkai.jdbc.dao2.UserDao;
import org.yangkai.jdbc.domain.Account;
import org.yangkai.jdbc.domain.User;

public class Test2 {

	public static void main(String[] args) {
		UserDao userdao=new UserDaoInstanceImpl();
		AccountDao accountdao=new AccountDaoInstanceImpl();
		
		User user=userdao.findUserById(7);
		String name=userdao.findUserName(6);
		Account account=accountdao.findAccountById(4);
		
		System.out.println("id:"+user.getId());
		System.out.println("name:"+user.getName());
		System.out.println("sex:"+user.getSex());
		System.out.println("birthday:"+user.getBirthday());
		System.out.println("addr:"+user.getAddr());
		System.out.println("-----------------------------");
		System.out.println("id:"+account.getId());
		System.out.println("name:"+account.getName());
		System.out.println("balance:"+account.getBalance());
		System.out.println("-----------------------------");
		System.out.println("name:"+name);

	}

}
