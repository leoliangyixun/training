package com.yangkai.sql.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.yangkai.sql.domain.User;
import com.yangkai.sql.util.JdbcDBConnection;

public class BatchAdd {
	private Connection conn;
	private PreparedStatement ps;

	public BatchAdd() {

	}

	public int[] addUsers(List<User> users) {
		conn = JdbcDBConnection.getConnection();
		int[] counts = null;
		try {
			String sql = "INSERT INTO user (u_name,u_sex,u_birth,u_addr) VALUES(?,?,?,?)";
			ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			for (int i = 0; i < users.size(); i++) {
				ps.setString(1, users.get(i).getUsername());
				ps.setString(2, users.get(i).getUserSex());
				ps.setObject(3, users.get(i).getUserBirthday());
				ps.setString(4, users.get(i).getUserAddress());
				ps.addBatch();
			}
			counts = ps.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcDBConnection.freeConnection(conn);
		}
		return counts;
	}
}
