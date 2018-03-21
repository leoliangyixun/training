package com.yangkai.sql.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.yangkai.sql.util.JdbcDBConnection;

public abstract class AbstractDaoImpl {
	
	public <T> List<T> template(Callback <T> callback)
	{
		Connection conn=JdbcDBConnection.getConnection();
		List<T> objs=null;
		objs=callback.doInCallback(conn);
		JdbcDBConnection.freeConnection(conn);
		return objs;
	}
	
	public  <T> List<T> query(final String sql,final Object[] args){
		
		return this.template(new Callback<T>() {

			@Override
			public List<T> doInCallback(Connection conn) {
				
				List<T> objs=null;
				if(args!=null){
					try {
						PreparedStatement ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						for(int i=0;i<args.length;i++){
							ps.setObject(i+1, args[i]);
						}
						ResultSet rs=ps.executeQuery();
						/*
						 * 对于结果集的处理有两种方式：
						 * 一种是调用抽象的方法，由子类去实现。
						 * 另一种就是调用 ResultSetMapper接口的mappingResultSet()方法。
						 * 下面用调用抽象的方法来处理结果集。
						 * */
						objs=mappingObjects(rs);
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}else{
					
				}
				return objs;
			}
		});
	}
	public <T> List<T>  queryAll(String sql){
		return this.query(sql, null);
	}
	
	public abstract <T> List<T> mappingObjects(ResultSet rs);
}
