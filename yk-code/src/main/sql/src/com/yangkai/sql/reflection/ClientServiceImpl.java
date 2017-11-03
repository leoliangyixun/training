package com.yangkai.sql.reflection;

import java.util.List;

public class ClientServiceImpl {
	private JdbcDaoTemplate dao=new JdbcDaoTemplate();
	public ClientServiceImpl() {
	}
	public <T> T find(final Class<T> clazz, final String sql, final int arg){
		List<T> objs=dao.find(clazz, sql, new Object[]{arg});
		return objs.get(0);
	}
	public <T> List<T> find(final Class<T> clazz, final String sql, final String arg){
		return dao.find(clazz, sql, new Object[]{arg});
	}
	public <T> List<T> find(final Class<T> clazz, final String sql, final String arg1, final String arg2){
		return dao.find(clazz, sql, new Object[]{arg1,arg2});
	}
	
	public <T>  List<T> find(final String sql, final int arg){
		return dao.find(sql, new Object[]{arg});
	}
}
