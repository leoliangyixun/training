package org.yangkai.jdbc.factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.yangkai.jdbc.tools.DBConnection;

public class ConnectionFactory {
	
	public static DataSource getDataSource()
	{
		return DBConnection.getDataSource();
	}
	public static Connection getConnection()
	{
		return DBConnection.getConnection();
		//return DBConnection.getPoolConnection();
	}
	public static void free(ResultSet rs)
	{
		DBConnection.free(rs);
	}

	public static void free(PreparedStatement ps,Connection conn)
	{
		DBConnection.free(ps, conn);
	}
	
	public static void free(ResultSet rs,PreparedStatement ps,Connection conn)
	{
		DBConnection.free(rs, ps, conn);
	}
}
