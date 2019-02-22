package org.yangkai.jdbc.dao.impl3;
import org.yangkai.jdbc.dao.Mapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.yangkai.jdbc.factory.ConnectionFactory;

public abstract class CommonDaoTemplate {
	
	public <T> T find(Class<T> clazz,String sql,Mapper<T> mapper)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		conn=ConnectionFactory.getConnection();
		T t=null;
		try {
			ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=ps.executeQuery();
			if(rs.next()){
				t=mapper.objectMapper(rs);
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
	
	public <T> List<T> findAll(Class<T> clazz,String sql,Mapper<T> mapper)
	{
		return null;
		
	}
}
