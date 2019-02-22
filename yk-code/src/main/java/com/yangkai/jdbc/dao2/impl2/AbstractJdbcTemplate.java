package org.yangkai.jdbc.dao2.impl2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

import org.yangkai.jdbc.factory.ConnectionFactory;
import org.yangkai.jdbc.tools.DaoException;

public abstract class AbstractJdbcTemplate {
	
	protected abstract <T> T doInCallback(Connection conn,PreparedStatement ps,ResultSet rs); 	
	protected abstract <T> T doInCallback(ResultSet rs) ;
	
	public <E> E find(final Class<E> clazz,final String sql)
	{

		 return null;
	}
	
	public <E> List<E> findAll(Class<E> clazz, String sql) 
	{
		
		return null;
	}

	public <E> boolean save(final E e) 
	{

		
		return false;
	}

	public int delete(final String sql) 
    {
		return 0;

	}
	
	public <E> int update(E e) 
	{
		
		return 0;
	}
	public <E> E commonAction()
	{
		Connection conn=ConnectionFactory.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		E obj=null;
		try{
			obj=this.doInCallback(conn,ps,rs);
		}catch (DaoException e) {
			e.printStackTrace();
			System.out.println("DaoException happened!!!");
		}
		ConnectionFactory.free(rs, ps, conn);
		return obj;
	}
}
