package org.yangkai.jdbc.dao.impl5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.yangkai.jdbc.factory.ConnectionFactory;

interface Mapper{
	public <T> T commonObjectMapper(ResultSet rs);
}
public abstract class CommonAbstractTemplate {
	
	public <T> T find(String sql,Object[] args,Mapper mapper)//参数中没有泛型。
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		conn=ConnectionFactory.getConnection();
		T t=null;
		try {
			ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			for(int i=0;i<args.length;i++)
			{
				ps.setObject(i+1, args[i]);
			}
			rs=ps.executeQuery();
			if(rs.next())
			{
				t=mapper.commonObjectMapper(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		ConnectionFactory.free(rs, ps, conn);
		return t;
	}
	
	public <T> List<T> findAll(String sql,Object[] args,Mapper mapper)
	{
		return null;
		
	}
	
	public <T> int add(T t)  
	{
		
		return 0;
	}


	public int delete(int id) 
	{
		
		return 0;
	}


	public <T> int update(T t) 
	{
		
		return 0;
	}
}
