package org.yangkai.jdbc.dao.impl2;

import org.yangkai.jdbc.dao.MyDAO;
import org.yangkai.jdbc.dao.DAO;
import org.yangkai.jdbc.domain.Account;
import org.yangkai.jdbc.domain.User;

public class Test {

	public static void main(String[] args) {
		MyDAO myDAO=new MyJdbcTemplate();
		
		String sql1="SELECT * FROM user WHERE id=7";
		String sql2="SELECT * FROM account WHERE id=4";
		User user=myDAO.find(User.class, sql1);
		Account account=myDAO.find(Account.class, sql2);
		
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
		
		User user1=myDAO.findById(User.class, 7);
		Account account1=myDAO.findById(Account.class, 4);
		System.out.println("id:"+user1.getId());
		System.out.println("name:"+user1.getName());
		System.out.println("sex:"+user1.getSex());
		System.out.println("birthday:"+user1.getBirthday());
		System.out.println("addr:"+user1.getAddr());
		System.out.println("-----------------------------");
		System.out.println("id:"+account1.getId());
		System.out.println("name:"+account1.getName());
		System.out.println("balance:"+account1.getBalance());
		System.out.println("-----------------------------");
	}

}
