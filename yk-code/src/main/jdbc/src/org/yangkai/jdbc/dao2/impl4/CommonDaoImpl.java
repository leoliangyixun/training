package org.yangkai.jdbc.dao2.impl4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.yangkai.jdbc.dao2.ObjectMapper;
import org.yangkai.jdbc.factory.ConnectionFactory;

public  class CommonDaoImpl {
	
	public Object find(String sql,Object[] args,ObjectMapper mapper)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		Object obj=null;
		conn=ConnectionFactory.getConnection();
		try {
			ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			for(int i=0;i<args.length;i++)
			{
				ps.setObject(i+1, args[i]);
			}
			rs=ps.executeQuery();
			if(rs.next())
			{
				obj=mapper.commonObjectMapper(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public List<Object> listFind(String sql,Object[] object,ObjectMapper mapper)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		List<Object> objs=null;
		Object obj=null;
		conn=ConnectionFactory.getConnection();
		try {
			ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			for(int i=0;i<object.length;i++)
			{
				ps.setObject(i+1, object[i]);
			}
			rs=ps.executeQuery();
			if(rs.isAfterLast()!=rs.isBeforeFirst())
			{
				objs=new ArrayList<Object>();
				while(rs.next())
				{
					obj=mapper.commonObjectMapper(rs);
					objs.add(obj);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objs;
	}
	public int action(String sql,Object[] object)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		int count=0;
		conn=ConnectionFactory.getConnection();
		try {
			ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			for(int i=0;i<object.length;i++)
			{
				ps.setObject(i+1, object[i]);
			}
			count=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
}
