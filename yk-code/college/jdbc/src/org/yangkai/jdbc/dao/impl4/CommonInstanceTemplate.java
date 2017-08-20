package org.yangkai.jdbc.dao.impl4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.yangkai.jdbc.factory.ConnectionFactory;

interface ObjectMapper{
	public <T> T commonObjectMapper(ResultSet rs,Class<T> clazz);
}
public  class CommonInstanceTemplate {
	
	public <T> T find(Class<T> clazz,String sql,Object[] args,ObjectMapper mapper)//参数中有泛型。
	{
		ResultSet rs=null;
		T t=null;
		rs=this.commonQuery(sql,args);
		try {
			while(rs.next())
			{
				t=mapper.commonObjectMapper(rs,clazz);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}
	
	public <T> List<T> findAll(Class<T> clazz,String sql,Object[] args,ObjectMapper mapper)
	{
		ResultSet rs=null;
		List<T> list=null;
		T t=null;
		rs=this.commonQuery(sql, args);
		try {
			if(!(rs.isAfterLast()==rs.isBeforeFirst()))
			{
				list=new ArrayList<T>();
				while(rs.next())
				{
					t=mapper.commonObjectMapper(rs,clazz);
					list.add(t);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/*
	public <T> List<T> findAll(Class<T> clazz,String sql,Object[] args,ObjectMapper2 mapper)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		conn=ConnectionFactory.getConnection();
		List<T> list=null;
		T t=null;
		try {
			ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			for(int i=0;i<args.length;i++)
			{
				ps.setObject(i+1, args[i]);
			}
			rs=ps.executeQuery();
			if(!(rs.isAfterLast()==rs.isBeforeFirst()))
			{
				list=new ArrayList<T>();
				while(rs.next())
				{
					t=mapper.commonObjectMapper(rs,clazz);
					list.add(t);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		ConnectionFactory.free(rs, ps, conn);
		return list;
		
	}
	*/
	public int commonUpdate(String sql,Object[] args)
	{
		Connection conn=null;
		PreparedStatement ps=null;
		int count=0;
		conn=ConnectionFactory.getConnection();
		try {
			ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			for(int i=0;i<args.length;i++)
			{
				ps.setObject(i+1, args[i]);
			}
			count=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public ResultSet commonQuery(String sql,Object[] args)
	{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		conn=ConnectionFactory.getConnection();
		try {
			ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			for(int i=0;i<args.length;i++)
			{
				ps.setObject(i+1, args[i]);
			}
			rs=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
