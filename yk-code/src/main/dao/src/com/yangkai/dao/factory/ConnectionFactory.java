package com.yangkai.dao.factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.yangkai.dao.tools.DBConnection;

public class ConnectionFactory {
	
	public static DataSource getDataSource()
	{
		return DBConnection.getDataSource();
	}
	public static Connection getConnection()
	{
		//Connection conn=DBConnection.getPoolConnection();
		//System.out.println(conn);
		//System.out.println(conn.getClass().getName());
		return DBConnection.getPoolConnection();
		//return conn;
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
