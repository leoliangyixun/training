package org.yangkai.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.yangkai.jdbc.factory.ConnectionFactory;

interface MyCallback<T>{
	 T doInCallback(Connection conn,PreparedStatement ps,ResultSet rs);
	
}
interface MyObjectMapper{
	<T> T commonObjectMapper(ResultSet rs);
	
}
public class InstanceBasicDAOTemplate<E> {
	public E find(String sql)
	{
		return null;
		
	}
	
	public List<E> findAll(String sql) 
	{
		
		return null;
	}

	public int save(E e) 
	{
		
		return 0;
	}

	public int delete(final String sql) 
    {
		   return this.commonTemplateMethod(new Callback<Integer>() {

			@Override
			public Integer doInCallback(Connection conn, PreparedStatement ps,ResultSet rs) 
			{
				int count = 0;
				try {
					ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					count=ps.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return count;
			}
		});
	}
	
	public int update(E e) 
	{
		
		return 0;
	}
	public <T> T commonTemplateMethod(Callback<T> callback)
	{
		Connection conn=ConnectionFactory.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		T returnType=callback.doInCallback(conn, ps, rs);
		ConnectionFactory.free(rs, ps, conn);
		System.out.println(returnType);
		return returnType;
	}
}
