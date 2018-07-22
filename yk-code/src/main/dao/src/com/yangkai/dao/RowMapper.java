package com.yangkai.dao;

import java.sql.ResultSet;

public interface RowMapper<T>{
	public T rowMapper(ResultSet rs);
}
