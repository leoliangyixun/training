package com.training.dao.college.dao.tools;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Properties;

import javax.naming.NamingException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
public class DBConnection{
	public final static String URL="jdbc:mysql://localhost:3306/jdbc";
	public final static String USER="root";
	public final static String PASSWORD="root";
	public final static String DRIVER="com.mysql.jdbc.Driver";
	public static DataSource ds=null;

	static{
		 try {
			 Properties dbcp=new Properties();
			 InputStream ins=DBConnection.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
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
	
	public static DataSource getDataSource() 
	{
		return ds;
	} 
	
	public static Connection getPoolConnection() 
	{
		Connection conn=null;
		if(ds!=null)
		{
			try {
			    conn=ds.getConnection();
		    } catch (SQLException e) {
			    e.printStackTrace();
		    }
		}
		return conn;
	}
	
	public static void free(ResultSet rs)
	{
		try {
				if(rs!=null)
				{
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public static void free(PreparedStatement ps,Connection conn)
	{
		try {
			if(ps!=null)
			{
				ps.close();
			}
			if(conn!=null)
			{
				conn.close();
			}	
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void free(ResultSet rs,PreparedStatement ps,Connection conn)
	{
		try {
			if(rs!=null)
			{
				rs.close();
			}
			if(ps!=null)
			{
				ps.close();
			}
			if(conn!=null)
			{
				conn.close();
				//System.out.println("�����ѹر�!!!");
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
