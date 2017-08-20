package com.yangkai.myblog.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnectionUtil {
	public final static String URL = "jdbc:mysql://localhost:3306/myblog";
	public final static String USER = "root";
	public final static String PASSWORD = "root";
	public final static String DRIVER = "com.mysql.jdbc.Driver";
	public static DataSource ds = null;
	// public static Context context=null;
	static {
		try {
			// context=(Context) new InitialContext().lookup("java:comp/env");
			// ds=(DataSource) context.lookup("jdbc/myblog");
			ds = (DataSource) new InitialContext()
					.lookup("java:comp/env/jdbc/myblog");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/*
	 * static{ try { Class.forName(DRIVER); } catch (ClassNotFoundException e) {
	 * e.printStackTrace(); } }
	 */

	// 从连接池获取连接
	public static Connection getPoolConnection() {
		Connection conn = null;
		if (ds != null) {
			try {
				// 从连接池中拿走一个连接。
				// ds= (DataSource) new
				// InitialContext().lookup("java:comp/env/jdbc/blog");
				conn = ds.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	// 利用DriverManager获得连接。
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void freeConnection(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void freeConnection(PreparedStatement pstmt, Connection conn) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void freeConnection(ResultSet rs, PreparedStatement pstmt,
			Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
