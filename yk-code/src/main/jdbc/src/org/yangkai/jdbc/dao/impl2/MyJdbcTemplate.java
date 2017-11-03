package org.yangkai.jdbc.dao.impl2;

import java.lang.reflect.Field;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.List;

import org.yangkai.jdbc.dao.MyDAO;
import org.yangkai.jdbc.factory.ConnectionFactory;

public class MyJdbcTemplate implements MyDAO{
	
	@Override
	public <T> List<T> findAll(Class<T> clazz, String sql) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
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
			if(rs.next()){	
				t=clazz.newInstance();
				for(int i=1;i<=rsmd.getColumnCount();i++)
				{
					//System.out.println(rsmd.getColumnName(i));
					Field f=clazz.getDeclaredField(rsmd.getColumnName(i));
					f.setAccessible(true);
					f.set(t, rs.getObject(rsmd.getColumnName(i)));
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
	public <T> boolean save(T t)
	{
		//System.out.println(t.getClass().getName());
		//System.out.println(t.getClass().getSimpleName());
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		boolean saveSuccess=false;
		conn=ConnectionFactory.getConnection();
		Field[] fs=t.getClass().getDeclaredFields();
		StringBuffer sb=new StringBuffer();
		String tableName=t.getClass().getSimpleName().toLowerCase();
		sb.append("INSERT INTO "+tableName+"(");
		for(int i=0;i<fs.length;i++)
		{
			//System.out.println(fs[i].getName());
			if(!fs[i].getName().equals("id"))
			{
				if(i==(fs.length-1))
				{
					sb.append(fs[i].getName());
				}else{
					sb.append(fs[i].getName()+",");
				}
			}
		}
		sb.append(") VALUES(");
		for(int i=0;i<fs.length;i++)
		{
			if(!fs[i].getName().equals("id"))
			{
				if(i==(fs.length-1))
				{
					sb.append("?");
				}else{
					sb.append("?,");
				}
		    }
	    }
		sb.append(")");
		String sql=sb.toString();
	   // System.out.println(sql);
		try {
			ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			int index=1;
			for(int i=0;i<fs.length;i++)
			{
				if(!fs[i].getName().equals("id"))
				{
					fs[i].setAccessible(true);
					ps.setObject(index, fs[i].get(t));//这里的索引号要灵活处理，不能直接用i来遍历。
					//System.out.println(index+"<------>"+fs[i].getName());
					index++;	
				}
			}
			int count=ps.executeUpdate();
			if(count>0)
			{
				saveSuccess=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		ConnectionFactory.free(rs, ps, conn);
		return saveSuccess;
	}

	@Override
	public <T> T findById(Class<T> clazz, int id) {
		String tableName=clazz.getSimpleName().toLowerCase();
		//System.out.println(tableName);
		String sql="SELECT * FROM "+tableName+" WHERE id="+id;
		return this.find(clazz, sql);
	}

	@Override
	public <T> T findByName(Class<T> clazz, String name) {
		String tableName=clazz.getSimpleName().toLowerCase();
		String sql="SELECT * FROM "+tableName+" WHERE name="+name;
		return this.find(clazz, sql);
	}
}
