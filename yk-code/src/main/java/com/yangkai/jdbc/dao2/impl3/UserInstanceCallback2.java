package org.yangkai.jdbc.dao2.impl3;

import java.util.List;

import org.yangkai.jdbc.domain.User;

public class UserInstanceCallback2 {
	JdbcInstanceCallbackTemplate template=new JdbcInstanceCallbackTemplate();
	public User find(int id){
		String sql="SELECT * FROM user WHERE id=?";
		Object[] args=new Object[]{id};
		long start=System.currentTimeMillis();
		User user=this.template.find(User.class,sql,args);
		long end=System.currentTimeMillis();
		System.out.println("instance2 method cost:"+(end-start));
		return user;
	}
	
	public List<User> findAll(int id){
		String sql="SELECT * FROM user WHERE id=?";
		Object[] args=new Object[]{id};
		long start=System.currentTimeMillis();
		List<User> users=this.template.findAll(User.class,sql,args);
		long end=System.currentTimeMillis();
		System.out.println("instance2 method cost:"+(end-start));
		return users;
	}
	
	public  int addUser(User user){
		return 0;
	}
}
