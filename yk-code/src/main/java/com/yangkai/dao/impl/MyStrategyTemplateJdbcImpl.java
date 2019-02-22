package com.yangkai.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yangkai.dao.Callback;
import com.yangkai.dao.ConnectionCallback;
import com.yangkai.dao.MyStrategyTemplate;
import com.yangkai.dao.RowMapper;
import com.yangkai.dao.factory.ConnectionFactory;

public class MyStrategyTemplateJdbcImpl implements MyStrategyTemplate{
	public <T> T execute(Callback<T> callback){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		conn=ConnectionFactory.getConnection();
		T target=callback.doInCallback(conn,ps,rs);
		ConnectionFactory.free(rs, ps, conn);
		return target;
	}
	public <T> T execute(ConnectionCallback<T> connectionCallback){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		conn=ConnectionFactory.getConnection();
		T t=connectionCallback.doInConnection(conn);
		ConnectionFactory.free(rs, ps, conn);
		return t;
	}
	
	@Override
	public <T> T query(final String sql,final Object[] args,final Class<T> clazz){
		return null;
	}
	/*
	 * 这里的T由RowMapper<T>中的 T 决定。
	 * */
	@Override
	public <T> T query(final String sql, final Object[] args, final RowMapper<T> rowMapper) {
		
//		return this.execute(new MyCallback<T>() {
			/*
			 * 方法一：
			 * */
//			@Override
//			public T doInCallback(Connection conn, PreparedStatement ps,ResultSet rs) {
//				T obj=null;
//				try {
//					ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
//					for(int i=0;i<args.length;i++){
//						ps.setObject(i+1, args[i]);
//					}
//					rs=ps.executeQuery();
//					while(rs.next()){
//						obj=rowMapper.rowMapper(rs);
//					}
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//				return obj;
//			}
//		});
	 
		return this.execute(new Callback<T>() {
			/*
			 * 方法二：
			 * */
			@Override
			public T doInCallback(Connection conn, PreparedStatement ps,ResultSet rs) {
				T objs=null;
				try {
					ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					for(int i=0;i<args.length;i++){
						ps.setObject(i+1, args[i]);
					}
					rs=ps.executeQuery();
					objs=rowMapper.rowMapper(rs);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return objs;
			}
		});
	}

	@Override
	public <T> T query(String sql, Object[] args,BeanPropertyRowMapper beanPropertyRowMapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T save(final ConnectionCallback<T> connectionCallback) {
		
//		return this.execute(new Callback<T>() {
//
//			@Override
//			public T doInCallback(Connection conn, PreparedStatement ps,ResultSet rs) {
//				return connectionCallback.doInConnection(conn);
//			}
//		});
		return this.execute(connectionCallback);
	}

	@Override
	public <T> T save(final String sql,final Object[] args) {
		
		return this.execute(new Callback<T>() {

			@Override
			public T doInCallback(Connection conn, PreparedStatement ps,ResultSet rs) {
				
				try {
					ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;	
			}
		});
	}
}
