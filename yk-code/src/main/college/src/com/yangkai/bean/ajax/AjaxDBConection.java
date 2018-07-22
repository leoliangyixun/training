package com.yangkai.bean.ajax;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AjaxDBConection {
	ResultSet rs = null;
	Statement stmt = null;
	Connection conn = null;
	String URL = "jdbc:mysql://localhost:3306/book?user=root&password=root";

	public AjaxDBConection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet myexecuteQuery(String sql) {
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.err.print(e.getMessage());
		}
		return rs;

	}

	public void closeConnection() {
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.getStackTrace();
		}
	}

	public String setCharacterEncoding(String str)
			throws UnsupportedEncodingException {
		return new String(str.getBytes("ISO-8859-1"), "GBK");
	}
}