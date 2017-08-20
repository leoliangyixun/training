package org.yangkai.jdbc.dao2.impl4;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import org.yangkai.jdbc.dao.DAO;
import org.yangkai.jdbc.factory.ConnectionFactory;

public class DaoImpl implements DAO{
	
	public <T> T find(Class<T> clazz,String sql)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		ResultSetMetaData rsmd=null;
		conn=ConnectionFactory.getConnection();
		T t=null;
		try {
			ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=ps.executeQuery();
			rsmd=rs.getMetaData();
			int count=rsmd.getColumnCount();
			String[] colFields=new String[count];
			for(int i=0;i<count;i++){
				colFields[i]=rsmd.getColumnName(i+1);
			}
			if(rs.next()){	
				t=clazz.newInstance();
				System.out.println(t.getClass().getName());
				for(int i=0;i<count;i++)
				{
					Field field=clazz.getDeclaredField(colFields[i]);
					field.setAccessible(true);
					field.set(t, rs.getObject(colFields[i]));
				}
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} 
		ConnectionFactory.free(rs, ps, conn);
		return t;
	}

	@Override
	public <T> boolean save(T t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> List<T> findAll(Class<T> clazz, String sql) {
		// TODO Auto-generated method stub
		return null;
	}
}
