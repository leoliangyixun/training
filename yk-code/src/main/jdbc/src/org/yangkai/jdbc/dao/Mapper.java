package org.yangkai.jdbc.dao;

import java.sql.ResultSet;

public interface Mapper<T>{
	public  T objectMapper(ResultSet rs);
}
