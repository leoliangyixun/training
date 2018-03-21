package com.training.dao.college.dao;

import java.sql.ResultSet;

public interface RowMapper<T>{
	public T rowMapper(ResultSet rs);
}
