package org.yangkai.jdbc.dao2;

import java.sql.ResultSet;

public interface RowMapper<T> {
	public  T doInResultSet(ResultSet rs);
}
