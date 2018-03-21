package org.yangkai.jdbc.dao;

public interface BasicDAO {
	public <T> T find(Class<T> clazz,String sql);
	public <T> T findAll(Class<T> clazz,String sql);
	public <T> T add(Class<T> clazz,T t);
	public <T> T delete(Class<T> clazz,String sql);
	public <T> T update(Class<T> clazz,String sql);
	
}
