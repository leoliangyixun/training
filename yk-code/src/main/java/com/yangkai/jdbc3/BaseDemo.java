package com.yangkai.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDemo {
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	String URL = "jdbc:mysql://localhost:3306/book?user=root&password=root";

	public BaseDemo() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ResultSet execute(String sql) {
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static void main(String[] args) throws NullPointerException {
		BaseDemo d = new BaseDemo();
		String sql = "SELECT * FROM bookinfo";
		// ResultSet rs=d.execute(sql);
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				System.out.println(rs.getObject("ISBN") + "~~~~"
						+ rs.getObject("name") + "~~~~"
						+ rs.getObject("publisher") + "~~~~"
						+ rs.getObject("price") + "~~~~" + rs.getObject("num"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
