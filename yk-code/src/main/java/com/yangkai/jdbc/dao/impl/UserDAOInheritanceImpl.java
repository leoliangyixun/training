package org.yangkai.jdbc.dao.impl;

import java.util.List;

import org.yangkai.jdbc.dao.UserDAO;
import org.yangkai.jdbc.domain.User;

public class UserDAOInheritanceImpl extends AbstractBasicDAOTemplate implements UserDAO{

	@Override
	public User getUser(int id) {
		Class<User> clazz=User.class;
		String sql="SELECT name,sex FROM user WHERE id="+id;
		User user=super.find(clazz, sql);
		return user;
	}

	@Override
	public User getUser(String name) {
		String sql="SELECT * FROM user WHERE name='"+name+"'";
		User user=super.find(User.class, sql);
		return user;
	}

	@Override
	public List<User> getAll(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUser(User user) {	
		return super.save(user);
	}

	@Override
	public boolean removeUser(int id) {
		
		String sql="DELETE FROM user WHERE id="+id;
		super.delete(sql);
		return false;
	} 

	@Override
	public boolean updateUser(User user) {
		super.update(user);
		return false;
	}

}
