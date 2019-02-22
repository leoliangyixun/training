package com.yangkai.dao;


import com.yangkai.dao.impl.BeanPropertyRowMapper;

public interface MyStrategyTemplate {
	public <T> T query(String sql,Object[] args,Class<T> clazz);
	public <T> T query(String sql,Object[] args,RowMapper<T> rowMapper);
	public <T> T query(String sql,Object[] args,BeanPropertyRowMapper BeanPropertyRowMapper);
	public <T> T save(String sql,Object[] args);
	public <T> T save(ConnectionCallback<T> connectionCallback);
}
