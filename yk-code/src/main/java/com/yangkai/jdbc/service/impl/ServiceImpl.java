package org.yangkai.jdbc.service.impl;

import org.yangkai.jdbc.dao.DAO;
import org.yangkai.jdbc.dao2.impl4.DaoImpl;
import org.yangkai.jdbc.dao2.impl4.DaoImpl2;
import org.yangkai.jdbc.domain.Account;
import org.yangkai.jdbc.domain.User;
import org.yangkai.jdbc.service.Service;

public class ServiceImpl implements Service {
	DAO dAO=new DaoImpl2();
	@Override
	public <T> T findUserById(Class<T> clazz,int id){
		//String tableName=clazz.toString().toLowerCase();
		//String sql="SELECT id,name,sex,birthday,addr FROM"+tableName+"WHERE id="+id;
		String sql="SELECT id,name,sex,birthday,addr FROM user WHERE id="+id;
		return dAO.find(clazz, sql);
	}
	
	@Override
	public <T> T findUserByName(Class<T> clazz,String name){
		//String tableName=clazz.toString().toLowerCase();
		//String sql="SELECT id,name,sex,birthday,addr FROM"+tableName+"WHERE name="+name;
		String sql="SELECT id,name,sex,birthday,addr FROM user WHERE name="+name;
		return dAO.find(clazz, sql);
	}
	
	@Override
	public <T> T findAccountById(Class<T> clazz, int id) {
		String sql="SELECT id,name,balance FROM account WHERE id="+id;
		return dAO.find(clazz, sql);
	}

	@Override
	public <T> T findAccountByName(Class<T> clazz, String name) {
		String sql="SELECT id,name,balance FROM account WHERE name="+name;
		return dAO.find(clazz, sql);
	}

	@Override
	public boolean saveUser(User user) {
		
		return dAO.save(user);
	}

	@Override
	public boolean saveAccount(Account account) {
		return dAO.save(account);
	}
}
