package org.yangkai.jdbc.dao2.impl4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.yangkai.jdbc.dao2.Mapper;
import org.yangkai.jdbc.factory.ConnectionFactory;

public abstract class CommonAbstractDaoTemplate {
	
	public <T> T find(Class<T> clazz,String sql,Mapper mapper)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		//ResultSetMetaData rsmd=null;
		conn=ConnectionFactory.getConnection();
		T t=null;
		try {
			ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=ps.executeQuery();
			if(rs.next()){
				t=mapper.commonResultSetMapper(rs);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} 
		ConnectionFactory.free(rs, ps, conn);
		return t;
	}
}
