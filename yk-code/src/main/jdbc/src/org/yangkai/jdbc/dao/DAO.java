package org.yangkai.jdbc.dao;

import java.util.List;

public interface DAO {

	public <T> List<T> findAll(Class<T> clazz,String sql);
	public <T> T find(Class<T> clazz,String sql);
	//public <T> T findById(Class<T> clazz,int id);
	//public <T> T findByName(Class<T> clazz,String name);
	//public Object find(Class clazz,String sql);
	public <T> boolean save(T t);
}
