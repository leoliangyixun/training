package org.yangkai.jdbc.dao2;

import java.sql.ResultSet;

public interface CommonMapper {
	public <T> T commonObjectMapper(ResultSet rs);
}
