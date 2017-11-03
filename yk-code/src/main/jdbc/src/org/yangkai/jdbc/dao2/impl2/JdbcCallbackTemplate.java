package org.yangkai.jdbc.dao2.impl2;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.yangkai.jdbc.dao2.ConnectionCallback;
import org.yangkai.jdbc.factory.ConnectionFactory;
import org.yangkai.jdbc.tools.DaoException;
interface Callback<T>{
	public T doInCallback(Connection conn,PreparedStatement ps,ResultSet rs);
}
public class JdbcCallbackTemplate {
	
	public <E> E find(final Class<E> clazz,final String sql,final Object[] args)
	{
		 E e=this.execute(new Callback<E>() {

			@Override
			public E doInCallback(Connection conn, PreparedStatement ps,ResultSet rs) 
			{
				E obj=null;
				try {
					ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					for(int i=0;i<args.length;i++){
						ps.setObject(i+1, args[i]);
					}
					rs=ps.executeQuery();
					ResultSetMetaData rsmd=rs.getMetaData();
					if(rs.next()){
						obj=clazz.newInstance();
						for(int i=1;i<=rsmd.getColumnCount();i++){
							Field f=clazz.getDeclaredField(rsmd.getColumnName(i));
							/*
							System.out.println("field name:"+f.getName()
									+"--->colume name:"+rsmd.getColumnName(i)
									+"--->colume value:"+rs.getObject(rsmd.getColumnName(i)));
							*/
							f.setAccessible(true);
							f.set(obj, rs.getObject(rsmd.getColumnName(i)));
						}
					}
				} catch (SQLException e) {	
					e.printStackTrace();
					//throw new DaoException(e.getMessage(), e);
				} catch (NoSuchFieldException e) {	
					e.printStackTrace();
					//throw new DaoException(e.getMessage(), e);
				} catch (SecurityException e) {		
					e.printStackTrace();
					//throw new DaoException(e.getMessage(), e);
				} catch (InstantiationException e) {
					e.printStackTrace();
					//throw new DaoException(e.getMessage(), e);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					//throw new DaoException(e.getMessage(), e);
				}
				return obj;
			}
		});
		return e;
	}
	
	public <E> List<E> findAll(final Class<E> clazz,final String sql,final Object[] args) 
	{
		 return this.execute(new Callback<List<E>>() {

				@Override
				public List<E> doInCallback(Connection conn, PreparedStatement ps,ResultSet rs) 
				{
					List<E> objs=null;
					try {
						ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						for(int i=0;i<args.length;i++){
							ps.setObject(i+1, args[i]);
						}
						rs=ps.executeQuery();
						if(!(rs.isAfterLast()==rs.isBeforeFirst())){
							objs=new ArrayList<E>();
							ResultSetMetaData rsmd=rs.getMetaData();
							while(rs.next()){
								E obj=clazz.newInstance();
								for(int i=1;i<=rsmd.getColumnCount();i++){
									Field f=clazz.getDeclaredField(rsmd.getColumnName(i));
									/*
									System.out.println("field name:"+f.getName()
											+"--->colume name:"+rsmd.getColumnName(i)
											+"--->colume value:"+rs.getObject(rsmd.getColumnName(i)));
									*/
											f.setAccessible(true);
											f.set(obj, rs.getObject(rsmd.getColumnName(i)));
											
								}
								objs.add(obj);
							}
						}
					} catch (SQLException e) {	
						e.printStackTrace();
						//throw new DaoException(e.getMessage(), e);
					} catch (NoSuchFieldException e) {	
						e.printStackTrace();
						//throw new DaoException(e.getMessage(), e);
					} catch (SecurityException e) {		
						e.printStackTrace();
						//throw new DaoException(e.getMessage(), e);
					} catch (InstantiationException e) {
						e.printStackTrace();
						//throw new DaoException(e.getMessage(), e);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						//throw new DaoException(e.getMessage(), e);
					}
					return objs;
				}
			});
	}

	public <T> int save(final ConnectionCallback<T> connectionCallback) 
	{
	    return this.execute(new Callback<Integer>() {

			@Override
			public Integer doInCallback(Connection conn, PreparedStatement ps,ResultSet rs) 
			{
				return (Integer) connectionCallback.doInConnection(conn,ps,rs);
			}
		});
	}
	
	public <T> T save2(final ConnectionCallback<T> connectionCallback) 
	{
	    return this.execute(new Callback<T>() {

			@Override
			public T doInCallback(Connection conn, PreparedStatement ps,ResultSet rs) 
			{
				return connectionCallback.doInConnection(conn,ps,rs);
			}
		});
	}

	public int delete(final String sql) 
    {
		   return this.execute(new Callback<Integer>() {

			@Override
			public Integer doInCallback(Connection conn, PreparedStatement ps,ResultSet rs) 
			{
				try {
					ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return 0;
			}
		});
	}
	
	public <E> int update(E e) 
	{
		
		return 0;
	}
	
	//统一获取连接和释放连接。
	public <E> E execute(Callback<E> callback)
	{
		Connection conn=ConnectionFactory.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		E obj=null;
		try{
			obj=callback.doInCallback(conn, ps, rs);
		}catch (DaoException e) {
			e.printStackTrace();
			System.out.println("DaoException happened!!!");
		}
		ConnectionFactory.free(rs, ps, conn);
		return obj;
	}
}
