package org.yangkai.jdbc.dao2.impl3;

import java.util.List;

import org.yangkai.jdbc.domain.User;

public class UserInstanceCallback {
	
	public User find(int id){
		JdbcInstanceCallbackTemplate template=new JdbcInstanceCallbackTemplate();
		String sql="SELECT * FROM user WHERE id=?";
		Object[] args=new Object[]{id};
		long start=System.currentTimeMillis();
		User user=template.find(User.class,sql,args);
		long end=System.currentTimeMillis();
		System.out.println("instance method cost:"+(end-start));
		return user;
	}
	
	public List<User> findAll(int id){	
		JdbcInstanceCallbackTemplate template=new JdbcInstanceCallbackTemplate();
		String sql="SELECT * FROM user WHERE id<?";
		Object[] args=new Object[]{id};
		long start=System.currentTimeMillis();
		List<User> users=template.findAll(User.class,sql,args);
		long end=System.currentTimeMillis();
		System.out.println("instance method cost:"+(end-start));
		return users;
	}
	
	public  int addUser(User user){
		return 0;
	}
}
