package com.yangkai.dao.impl2;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.yangkai.dao.factory.ConnectionFactory;

interface TemplateCallback<T>{
	public T doInTemplateCallback(Connection conn,PreparedStatement ps,ResultSet rs);
}
public class BeanPropertyRowMapper {
	private Class<?> clazz;
	public BeanPropertyRowMapper(Class<?> clazz){
		this.clazz=clazz;
		//System.out.println("BeanPropertyRowMapper Constructor:"+clazz.getSimpleName());	
	}
	public <T> T query(final String sql,final Object[] args){
	
		return this.execute(new TemplateCallback<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T doInTemplateCallback(Connection conn,PreparedStatement ps, ResultSet rs) {
				T obj=null;
				try {
					ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					for(int i=0;i<args.length;i++){
						ps.setObject(i+1, args[i]);
					}
					rs=ps.executeQuery();
					ResultSetMetaData rsmd=rs.getMetaData();
					if(rs.next()){
						obj=(T)clazz.newInstance();
						System.out.println("通过反射构造的对象是："+obj.getClass().getName());
						for(int i=1;i<=rsmd.getColumnCount();i++){
							Field f=clazz.getDeclaredField(rsmd.getColumnName(i));
							f.setAccessible(true);
							f.set(obj, rs.getObject(rsmd.getColumnName(i)));
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
				return obj;
			}
		});
	}
	
	public <T> T execute(TemplateCallback<T> templateCallback){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		conn=ConnectionFactory.getConnection();
		T t=templateCallback.doInTemplateCallback(conn,ps,rs);
		ConnectionFactory.free(rs, ps, conn);
		return t;
	}
}
