package org.yangkai.jdbc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.yangkai.jdbc.dao.UserMapper;
import org.yangkai.jdbc.domain.User;

public class UserDaoImpl2 extends UserAbstractDaoImpl2{
	public User findUserName(User user)
	{
		String sql="SELECT * FROM user WHERE id=?";
		Object[] fields={user.getId()};
		Object u=super.find(sql, fields,new UserMapper() 
		{
			public Object userResultSetMapping(ResultSet rs) 
			{
				User user=new User();
				try {
					user.setName(rs.getString("name"));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return user;
			}
		});
		return (User)u;	
	}
	public User findUser(User user)
	{
		String sql="SELECT name FROM user WHERE id=?";
		Object[] fields={user.getId()};
		Object u=super.find(sql, fields,new UserMapper() {
			
			@Override
			public Object userResultSetMapping(ResultSet rs) 
			{
				User user=new User();

				return user;
			}
		});
		return (User)u;	
	}
}
