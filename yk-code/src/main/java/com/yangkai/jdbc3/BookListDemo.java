package com.yangkai.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.NullPointerException;
import java.sql.Statement;

public class BookListDemo {
	private static ResultSet rs = null;
	private static Statement stmt = null;
	private static Connection conn = null;
	String url = "jdbc:mysql://localhost:3306/book?user=root&password=root";

	public BookListDemo() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
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

	public static void main(String[] args) throws NullPointerException,
			SQLException {
		BookListDemo d = new BookListDemo();
		String sql = "select * from bookinfo";
		rs = stmt.executeQuery(sql);
		// ResultSet rs=d.myexecuteQuery(sql);
		try {
			while (rs.next()) {
				System.out.println(rs.getString(1) + "--" + rs.getString(2)
						+ "--" + rs.getString(3) + "--" + rs.getFloat(4) + "--"
						+ rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		rs.close();
	}
}