package org.yangkai.jdbc.dao2.impl4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import org.yangkai.jdbc.dao2.Mapper;
import org.yangkai.jdbc.factory.ConnectionFactory;

public  class CommonDaoImpl3 {
	
	public Object find(String sql,Object[] args,Mapper mapper)
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
				obj=mapper.commonResultSetMapper(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public List<Object> findAll(String sql, Object[] object, Mapper mapper) {
		// TODO Auto-generated method stub
		return null;
	}

	public int action(String sql, Object[] object) {
		// TODO Auto-generated method stub
		return 0;
	}
}
