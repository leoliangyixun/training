package org.yangkai.jdbc.dao2.impl3;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.yangkai.jdbc.dao2.Callback;
import org.yangkai.jdbc.factory.ConnectionFactory;
import org.yangkai.jdbc.tools.DaoException;

public class JdbcStaticCallbackTemplate {
	
	public static <E> E find(final Class<E> clazz,final String sql,final Object[] args)
	{
		 E e=execute(new Callback<E>() {

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
	
	public static <E> List<E> findAll(final Class<E> clazz,final String sql,final Object[] args) 
	{
		 return execute(new Callback<List<E>>() {

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

	public static <E> int save(final E e) 
	{
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
	
	    return execute(new Callback<Integer>() {

			@Override
			public Integer doInCallback(Connection conn, PreparedStatement ps,ResultSet rs) 
			{
				int count=0;
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
					count=ps.executeUpdate();
				} catch (SQLException ex) {
					ex.printStackTrace();
				} catch (IllegalArgumentException ex) {
					ex.printStackTrace();
				} catch (IllegalAccessException ex) {
					ex.printStackTrace();
				}
				return count;
			}
		});
	}

	public static int delete(final String sql) 
    {
		   return execute(new Callback<Integer>() {

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
	
	public static <E> int update(E e) 
	{
		
		return 0;
	}
	
	//统一获取连接和释放连接。
	public static <E> E execute(Callback<E> callback)
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
