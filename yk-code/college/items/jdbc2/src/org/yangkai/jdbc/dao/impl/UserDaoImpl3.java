package org.yangkai.jdbc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.yangkai.jdbc.dao.UserMapper;
import org.yangkai.jdbc.domain.User;

public class UserDaoImpl3 extends UserAbstractDaoImpl2{
	public User findUser(User user)
	{
		String sql="SELECT * FROM user WHERE id=?";
		Object[] fields={user.getId()};
		//Object u=super.find(sql, fields,Mapper mapper);
		//return (User)u;	
		return null;
	}
	public  Object userResultSetMapping(ResultSet rs)
	{
		User user=new User();
		return user;
	}
}
