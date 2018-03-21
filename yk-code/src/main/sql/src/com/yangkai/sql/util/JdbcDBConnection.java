package com.yangkai.sql.util;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;

import javax.naming.NamingException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
public class JdbcDBConnection{
	public final static String URL="jdbc:mysql://localhost:3306/jdbc";
	public final static String USER="root";
	public final static String PASSWORD="root";
	public final static String DRIVER="com.mysql.jdbc.Driver";
	public static DataSource ds=null;

	static{
		 try {
			 Properties dbcp=new Properties();
			 InputStream ins=JdbcDBConnection.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
			 dbcp.load(ins);
			 ds=BasicDataSourceFactory.createDataSource(dbcp);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static{
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static DataSource getDataSource() 
	{
		return ds;
	} 
	
	public static Connection getConnection() 
	{
		Connection conn=null;
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static Connection getPoolConnection() 
	{
		Connection conn=null;
		if(ds!=null){
			try {
			    conn=ds.getConnection();
		    } catch (SQLException e) {
			    e.printStackTrace();
		    }
		}
		return conn;
	}
	
	public static void freeConnection(Connection conn)
	{
		try {
			if(conn!=null)
			{
				conn.close();
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}
}
