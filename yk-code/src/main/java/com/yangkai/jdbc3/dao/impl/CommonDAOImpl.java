package com.yangkai.jdbc.dao.impl;

import java.text.DateFormat;

import com.yangkai.jdbc.dao.AbstractCommonDAO;
import com.yangkai.jdbc.domain.Accountant;
import com.yangkai.jdbc.domain.User;

public class CommonDAOImpl extends AbstractCommonDAO{

	public CommonDAOImpl() {
		
	}
	public int insertUser(User user)
	{
		String sql="INSERT INTO user(name,job,hiredate,salary) VALUES(?,?,?,?)";
		Object[] fields={user.getName(),user.getJob(),DateFormat.getDateInstance().format(user.getHiredate()),user.getSalary()};
		return super.insert(sql, fields);			
	}
	public int insertAccountant(Accountant account)
	{
		String sql="INSERT INTO accountant(name,account,money) VALUES(?,?,?)";
		Object[] fields={account.getName(),account.getAccount(),account.getMoney()};
		return super.insert(sql, fields);			
	}
	public User findUser(String name)
	{
		String sql="SELECT * FROM user WHERE name=?";
		User user=null;
		return user; 
	}
	
} 
