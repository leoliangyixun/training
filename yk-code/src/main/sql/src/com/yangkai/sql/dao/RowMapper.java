package com.yangkai.sql.dao;

import java.sql.ResultSet;
import java.util.List;

public interface RowMapper<T> {
	public List<T> mappingRow(ResultSet rs);

}
