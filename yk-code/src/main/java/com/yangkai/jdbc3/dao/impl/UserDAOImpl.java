package com.yangkai.jdbc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.yangkai.jdbc.dao.UserAbstractDAO;
import com.yangkai.jdbc.domain.User;

public class UserDAOImpl extends UserAbstractDAO{
	public User findUser(User user)
	{
		String sql="SELECT * FROM user WHERE id=?";
		Object[] fields={user.getId()};
		Object u=super.find(sql, fields);
		return (User)u;	
	}
	public  Object userResultSetMapping(ResultSet rs)
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
}
