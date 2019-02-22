package org.yangkai.jdbc.dao2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface ConnectionCallback<T> {
	public  T doInConnection(Connection conn,PreparedStatement ps,ResultSet rs);
}
