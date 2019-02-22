package com.yangkai.sql.strategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.yangkai.sql.dao.RowMapper;
import com.yangkai.sql.util.JdbcDBConnection;
public  class MyJdbcTemplate {

	public <T> List<T> template(Callback<T> callback) {
		Connection conn = JdbcDBConnection.getConnection();
		List<T> objs = null;
		objs = callback.doInCallback(conn);
		JdbcDBConnection.freeConnection(conn);
		return objs;
	}

	public <T> List<T> query(final String sql, final Object[] args, final RowMapper<T> mapper) {

		return this.template(new Callback<T>() {

			@Override
			public List<T> doInCallback(Connection conn) {
				List<T> objs = null;
				if (args != null) {
					try {
						PreparedStatement ps = conn.prepareStatement(sql,
								ResultSet.TYPE_SCROLL_SENSITIVE,
								ResultSet.CONCUR_UPDATABLE);
						for (int i = 0; i < args.length; i++) {
							ps.setObject(i + 1, args[i]);
						}
						ResultSet rs = ps.executeQuery();
						/*
						 * ���ڽ�����Ĵ��������ַ�ʽ�� һ���ǵ��ó���ķ�����������ȥʵ�֡� ��һ�־��ǵ���
						 * ResultSetMapper�ӿڵ�mappingResultSet()������
						 * ������ʵ��ResultSetMapper�ӿ�������������
						 */
						objs=mapper.mappingRow(rs);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} 
				return objs;
			}
		});
	}

	public <T> List<T> queryAll(String sql, RowMapper<T> mapper) {
		return this.query(sql, null, mapper);
	}
}
