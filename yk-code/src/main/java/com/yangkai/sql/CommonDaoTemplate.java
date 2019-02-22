package com.yangkai.sql;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import com.yangkai.sql.util.JdbcDBConnection;

interface Callback<T>{
	 T doInCallback(Connection conn);	
}
public abstract class CommonDaoTemplate {
	
	public <E> E find(final Class<E> clazz,final String sql)
	{
		 E e=this.template(new Callback<E>() {
			@Override
			public E doInCallback(Connection conn) {
				PreparedStatement ps=null;
				ResultSet rs=null;
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
		for(int i=0;i<fs.length;i++){
			if(!fs[i].getName().equals("id")){
				sb.append(fs[i].getName()+",");
			}
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(") VALUES(");
		for(int i=0;i<fs.length;i++){
			if(!fs[i].getName().equals("id")){
				sb.append("?,");
		    }
	    }
		sb.deleteCharAt(sb.length()-1);
		sb.append(")");
		final String sql=sb.toString();
		System.out.println(sql);
	    saveSuccess=this.template(new Callback<Boolean>() {
			@Override
			public Boolean doInCallback(Connection conn) {
				PreparedStatement ps=null;
				boolean saveSuccess=false;
				try {
					ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					int index=1;
					for(int i=0;i<fs.length;i++){
						if(!fs[i].getName().equals("id")){
							fs[i].setAccessible(true);
							ps.setObject(index, fs[i].get(e));//这里的索引号要灵活处理，不能直接用i来遍历。
							System.out.println(index+"<------>"+fs[i].getName()+":"+fs[i].get(e));
							index++;	
						}
					}
					int count=ps.executeUpdate();
					if(count>0){
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
			public Integer doInCallback(Connection conn) {
				PreparedStatement ps=null;
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
	/*
	 * 该方法的目的就是创建数据库链接与关闭链接。
	 * */
	public <E> E template(Callback<E> callback)
	{
		Connection conn=JdbcDBConnection.getConnection();
		E obj=null;
		obj=callback.doInCallback(conn);
		JdbcDBConnection.freeConnection(conn);
		return obj;
	}
}
