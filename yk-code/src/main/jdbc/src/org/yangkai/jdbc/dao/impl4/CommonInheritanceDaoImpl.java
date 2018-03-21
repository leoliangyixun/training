package org.yangkai.jdbc.dao.impl4;

import java.lang.reflect.Field;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.List;

import org.yangkai.jdbc.dao.CommonDAO;

public class CommonInheritanceDaoImpl extends CommonAbstractTemplate implements CommonDAO{

	public <T> T findById(Class<T> clazz,int id) {
		String tableName=clazz.getSimpleName().toLowerCase();
		String sql="SELECT * FROM "+tableName+" WHERE id=?";
		//System.out.println(sql);
		Object[] args={id};
		T t=super.find(clazz,sql, args,new Mapper() 
		{
			@SuppressWarnings("hiding")
			@Override
			public  <T> T commonObjectMapper(ResultSet rs,Class<T> clazz) { 
				T t=null;
				try {
					ResultSetMetaData rsmd=rs.getMetaData();
				    t=clazz.newInstance();
					for(int i=1;i<=rsmd.getColumnCount();i++)
					{
						Field f=clazz.getDeclaredField(rsmd.getColumnName(i));
						//System.out.println(rsmd.getColumnName(i));
						f.setAccessible(true);
						f.set(t, rs.getObject(rsmd.getColumnName(i)));
					}
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
				return t;
			}
		});
		return t;
	}

	@Override
	public <T> List<T> findAll(Class<T> clazz, int id) {
		String tableName=clazz.getSimpleName().toLowerCase();
		String sql="SELECT * FROM "+tableName+" WHERE id<?";
		Object[] args={id};
		List<T> list=super.findAll(clazz,sql, args,new Mapper() {
			@SuppressWarnings("hiding")
			@Override
			public <T> T commonObjectMapper(ResultSet rs,Class<T> clazz) { 
				T t=null;
				try {
					ResultSetMetaData rsmd=rs.getMetaData();
				    t=clazz.newInstance();
					for(int i=1;i<=rsmd.getColumnCount();i++)
					{
						Field f=clazz.getDeclaredField(rsmd.getColumnName(i));
						//System.out.println(rsmd.getColumnName(i));
						f.setAccessible(true);
						f.set(t, rs.getObject(rsmd.getColumnName(i)));
					}
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
				return t;
			}
		});
		return list;
	}
	public <T> int save(T t)
	{
		
		return 0;
	}
}
