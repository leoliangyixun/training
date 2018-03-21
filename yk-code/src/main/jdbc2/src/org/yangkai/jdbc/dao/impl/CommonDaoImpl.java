package org.yangkai.jdbc.dao.impl;

import java.text.DateFormat;

import org.yangkai.jdbc.domain.Account;
import org.yangkai.jdbc.domain.Employe;


public class CommonDaoImpl extends AbstractCommonDaoImpl{

	public CommonDaoImpl() {
		
	}
	public int insertUser(Employe employe)
	{
		String sql="INSERT INTO emp(name,job,hiredate,sal) VALUES(?,?,?,?)";
		Object[] fields={employe.getName(),employe.getJob(),DateFormat.getDateInstance().format(employe.getHiredate()),employe.getSal()};
		return super.insert(sql, fields);			
	}
	public int insertAccount(Account account)
	{
		String sql="INSERT INTO account(id,balance) VALUES(?,?,?)";
		Object[] fields={account.getId(),account.getBalance()};
		return super.insert(sql, fields);			
	}
	public Employe findUser(String name)
	{
		String sql="SELECT * FROM user WHERE name=?";
		Employe user=null;
		return user; 
	}
	
} 
