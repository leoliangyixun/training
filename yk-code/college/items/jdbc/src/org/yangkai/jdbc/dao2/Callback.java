package org.yangkai.jdbc.dao2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface Callback<T> {
	public  T doInCallback(Connection conn,PreparedStatement ps,ResultSet rs);
}
