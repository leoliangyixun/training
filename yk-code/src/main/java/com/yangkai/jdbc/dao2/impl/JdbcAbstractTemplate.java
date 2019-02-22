package org.yangkai.jdbc.dao2.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import org.yangkai.jdbc.dao2.RowMapper;
import org.yangkai.jdbc.factory.ConnectionFactory;

public abstract class JdbcAbstractTemplate {
	
	public <T> T find(Class<T> clazz,String sql,Object[] args,RowMapper<T> mapper)
	{
		return this.executeCallback(sql,args,mapper);
	}
	
	public <T> List<T> findAll(Class<T> clazz,String sql,Object[] args,RowMapper<List<T>> mapper)
	{
		return this.executeCallback(sql, args, mapper);
		//return this.executeCallbackList(sql, args, mapper);
	}
	/*
	 *这里的T即可指代对象，也可指代集合，这取决于传进来的T是什么形式。
	 *如果传进来的T表示对象，则返回的T也就代表对象，如果传进来的T代表集合，这返回的T就代表集合，
	 *也就是说T可以代表任意类型，绝不局限于对象。
	 * */
	public <T> T executeCallback(String sql,Object[] args,RowMapper<T> mapper)
	{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		conn=ConnectionFactory.getConnection();
		//System.out.println(conn);
		try {
			ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			for(int i=0;i<args.length;i++){
				ps.setObject(i+1, args[i]);
			}
			//只能执行查询，这个方法已经写死了，不太灵活。
			rs=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		T t=mapper.doInResultSet(rs);
		ConnectionFactory.free(rs, ps, conn);
		return t;
	}
	//多余方法。
	public <T> List<T> executeCallbackList(String sql,Object[] args,RowMapper<List<T>> mapper)
	{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		conn=ConnectionFactory.getConnection();
		try {
			ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			for(int i=0;i<args.length;i++){
				ps.setObject(i+1, args[i]);
			}
			rs=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<T> t=mapper.doInResultSet(rs);
		ConnectionFactory.free(rs, ps, conn);
		return t;
	}
	
}
