package org.yangkai.jdbc.dao;

import java.util.List;

public interface CommonDAO {
	public <T> T findById(Class<T> clazz,int id);
	public <T> List<T> findAll(Class<T> clazz,int id);
}
