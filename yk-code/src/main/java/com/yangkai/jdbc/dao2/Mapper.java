package org.yangkai.jdbc.dao2;

import java.sql.ResultSet;

public interface Mapper {
	
	public <T> T commonResultSetMapper(ResultSet rs);
	//public <T> T commonResultSetMapper(ResultSet rs,Class<?> clazz);
}
