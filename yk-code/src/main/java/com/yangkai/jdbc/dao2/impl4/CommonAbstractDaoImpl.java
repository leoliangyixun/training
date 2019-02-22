package org.yangkai.jdbc.dao2.impl4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.yangkai.jdbc.factory.ConnectionFactory;

public abstract class CommonAbstractDaoImpl {
	public List<Object> commonFind(String sql,Object[] args)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		List<Object> objs=null;
		Object obj=null;
		conn=ConnectionFactory.getConnection();
		try {
			ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			for(int i=0;i<args.length;i++)
			{
				ps.setObject(i+1, args[i]);
			}
			rs=ps.executeQuery();
			if(rs.isAfterLast()!=rs.isBeforeFirst())
			{
				objs=new ArrayList<Object>();
				while(rs.next())
				{
					obj=this.objectMapper(rs);
					objs.add(obj);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objs;
	}
	public int commonAction(String sql,Object[] object)
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
	
	public abstract Object objectMapper(ResultSet rs);
	
}
