package org.yangkai.jdbc.dao.impl;

import java.util.List;

import org.yangkai.jdbc.dao.UserDAO;
import org.yangkai.jdbc.domain.User;

public class UserDAOInstanceImpl implements UserDAO{
	InstanceBasicDAOTemplate<User> template=new InstanceBasicDAOTemplate<User>();
	
	@Override
	public User getUser(int id) {
		String sql="SELECT * FROM user WHERE id=?";
		User user=this.template.find(sql);
		return user;
	}

	@Override
	public User getUser(String name) {
		
		return null;
	}

	@Override
	public List<User> getAll(int id) {
		
		return null;
	}

	@Override
	public boolean addUser(User user) {
		
		return false;
	}

	@Override
	public boolean removeUser(int id) {
		String sql="DELETE FROM user WHERE id="+id;
		
		this.template.delete(sql);
		return false;
	} 

	@Override
	public boolean updateUser(User user) {
		
		 this.template.update(user);
		 return false;
	}

}
