package org.yangkai.jdbc.dao.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import org.yangkai.jdbc.factory.ConnectionFactory;
import org.yangkai.jdbc.tools.DaoException;

interface Callback<T>{
	 T doInCallback(Connection conn,PreparedStatement ps,ResultSet rs);	
}
public abstract class AbstractBasicDAOTemplate {
	
	public <E> E find(final Class<E> clazz,final String sql)
	{
		 E e=this.template(new Callback<E>() {

			@Override
			public E doInCallback(Connection conn, PreparedStatement ps,ResultSet rs) 
			{
				E instance=null;
				try {
					ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					rs=ps.executeQuery();
					ResultSetMetaData rsmd=rs.getMetaData();
					if(rs.next()){
						instance=clazz.newInstance();
						for(int i=1;i<=rsmd.getColumnCount();i++){
     						Field f=clazz.getDeclaredField(rsmd.getColumnName(i));
//							System.out.println("Field:"+f);
//							System.out.println("field name:"+f.getName()+"--->colume name:"+rsmd.getColumnName(i)+"--->colume value:"+rs.getObject(rsmd.getColumnName(i)));
							f.setAccessible(true);
//							f.set(instance, rs.getObject(rsmd.getColumnName(i)));
							f.set(instance, rs.getObject(f.getName()));
						}
					}
				} catch (SQLException e) {	
					e.printStackTrace();
//					throw new DaoException(e.getMessage(), e);
				} catch (NoSuchFieldException e) {	
					e.printStackTrace();
//					throw new DaoException(e.getMessage(), e);
				} catch (SecurityException e) {		
					e.printStackTrace();
//					throw new DaoException(e.getMessage(), e);
				} catch (InstantiationException e) {
					e.printStackTrace();
//					throw new DaoException(e.getMessage(), e);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
//					throw new DaoException(e.getMessage(), e);
				}
				return instance;
			}
		});
		 return e;
	}
	
	public <E> List<E> findAll(Class<E> clazz, String sql) 
	{
		
		return null;
	}

	public <E> boolean save(final E e) 
	{
		boolean saveSuccess=false;
		final Field[] fs=e.getClass().getDeclaredFields();
		StringBuffer sb=new StringBuffer();
		String tableName=e.getClass().getSimpleName().toLowerCase();
		sb.append("INSERT INTO "+tableName+"(");
		for(int i=0;i<fs.length;i++)
		{
			if(!fs[i].getName().equals("id"))
			{
				sb.append(fs[i].getName()+",");
			}
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(") VALUES(");
		for(int i=0;i<fs.length;i++)
		{
			if(!fs[i].getName().equals("id"))
			{
				sb.append("?,");
		    }
	    }
		sb.deleteCharAt(sb.length()-1);
		sb.append(")");
		final String sql=sb.toString();
		System.out.println(sql);
	
	    saveSuccess=this.template(new Callback<Boolean>() {

			@Override
			public Boolean doInCallback(Connection conn, PreparedStatement ps,ResultSet rs) 
			{
				boolean saveSuccess=false;
				try {
					ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					int index=1;
					for(int i=0;i<fs.length;i++)
					{
						if(!fs[i].getName().equals("id"))
						{
							fs[i].setAccessible(true);
							ps.setObject(index, fs[i].get(e));//这里的索引号要灵活处理，不能直接用i来遍历。
							System.out.println(index+"<------>"+fs[i].getName()+":"+fs[i].get(e));
							index++;	
						}
					}
					int count=ps.executeUpdate();
					if(count>0)
					{
						saveSuccess=true;
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				} catch (IllegalArgumentException ex) {
					ex.printStackTrace();
				} catch (IllegalAccessException ex) {
					ex.printStackTrace();
				}
				return saveSuccess;
			}
		});
		
		return saveSuccess;
	}

	public int delete(final String sql) 
    {
		   return this.template(new Callback<Integer>() {

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
	public <E> E template(Callback<E> callback)
	{
		Connection conn=ConnectionFactory.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		E obj=null;
		try{
			obj=callback.doInCallback(conn, ps, rs);
		}catch (DaoException daoException) {
			System.out.println("DaoException happened!!!");
			/*
			 * 这里的自定义异常是为了将可能出现的编译时异常转化为运行时异常，防止程序不能通过编译。
			 * 也就是说，程序在此处及时出现了编译时异常，代码仍然能够向下执行。
			 * */
			throw daoException;
		}
		ConnectionFactory.free(rs, ps, conn);
		return obj;
	}
}
