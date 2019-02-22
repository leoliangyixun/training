package com.yangkai.sql.reflection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yangkai.sql.dao.Callback;
import com.yangkai.sql.util.JdbcDBConnection;

public class JdbcDaoTemplate {
	private Class<?> classType;
	
	public JdbcDaoTemplate() {
	}
	
	public <T> T execute(Callback<T> callback){
		Connection conn=null;
		conn=JdbcDBConnection.getConnection();
		T t=callback.doInCallback(conn);
		JdbcDBConnection.freeConnection(conn);
		return t;
	}
	
	public void setClassType(Class<?> classType){
		this.classType=classType;
	}
	
	public <T>  List<T> find(final String sql, final Object... args){
		return this.execute(new Callback< List<T>>() {
			@Override
			public   List<T> doInCallback(Connection conn) {
				List<T> objs=null;
				return objs;
			}
		});
	}
	
	/*
	 * 最好用Class<T> classType代替Class<?> classType，这样可以避免强制类型转换。
	 * */
	public <T> List<T> find(final Class<T> clazz, final String sql, final Object... args){
	
		return this.execute(new Callback< List<T>>() {
			@Override
			public   List<T> doInCallback(Connection conn) {
				 List<T> objs=null;
				 PreparedStatement ps=null;
				 ResultSet rs=null;
				 ResultSetMetaData rsmd=null;
				try {
					ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					for(int i=0;i<args.length;i++){
						ps.setObject((i+1), args[i]);
					}
					rs=ps.executeQuery();
					rsmd=rs.getMetaData();
					while(rs.next()){
						if(objs==null){
							objs=new ArrayList<T>();
						}
						T obj=clazz.newInstance();
						for(int j=1;j<=rsmd.getColumnCount();j++)
						{
							
//							System.out.println(rsmd.getColumnName(j)+"<------>"
//									+rsmd.getColumnLabel(j)+"<------>"
//									+rsmd.getColumnTypeName(j)+"<------>"
//									+rsmd.getColumnType(j)+"<------>"
//									+rsmd.getTableName(j));
						}
					}
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
				return objs;
			}
		});
	}
}	


