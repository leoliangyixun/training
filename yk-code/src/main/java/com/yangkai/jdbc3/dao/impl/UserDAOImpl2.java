package com.yangkai.jdbc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.yangkai.jdbc.dao.UserAbstractDAO2;
import com.yangkai.jdbc.dao.UserDAOMapping;
import com.yangkai.jdbc.domain.User;

public class UserDAOImpl2 extends UserAbstractDAO2{
	public User findUserName(User user)
	{
		String sql="SELECT * FROM user WHERE id=?";
		Object[] fields={user.getId()};
		Object u=super.find(sql, fields,new UserDAOMapping() 
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
		Object u=super.find(sql, fields,new UserDAOMapping() {
			
			@Override
			public Object userResultSetMapping(ResultSet rs) 
			{
				User user=new User();
				try {
					user.setName(rs.getString("name"));
					user.setJob(rs.getString("job"));
					user.setHiredate(rs.getDate("hiredate"));
					user.setSalary(rs.getFloat("salary"));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return user;
			}
		});
		return (User)u;	
	}
}
