package org.yangkai.jdbc.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
public class ConnectionTool{
	public final static String URL="jdbc:mysql://localhost:3306/dept";
	public final static String USER="root";
	public final static String PASSWORD="root";
	public final static String DRIVER="com.mysql.jdbc.Driver";
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
	
	public static Connection getPoolConnection() 
	{
		DataSource ds=null;
		Context context=null;
		Connection conn=null;
		try {
			 context=(Context) new InitialContext().lookup("java:comp/env");
			 ds=(DataSource) context.lookup("jdbc/test");
			 if(ds!=null)
			 {
				 try {
					conn=ds.getConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			 }
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void freeConnection(ResultSet rs)
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

	public static void freeConnection(PreparedStatement pstmt,Connection conn)
	{
		try {
			if(pstmt!=null)
			{
				pstmt.close();
			}
			if(conn!=null)
			{
				conn.close();
			}	
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void freeConnection(ResultSet rs,PreparedStatement pstmt,Connection conn)
	{
		try {
			if(rs!=null)
			{
				rs.close();
			}
			if(pstmt!=null)
			{
				pstmt.close();
			}
			if(conn!=null)
			{
				conn.close();
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
