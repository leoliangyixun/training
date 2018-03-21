package org.yangkai.jdbc.dao2.impl4;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.yangkai.jdbc.factory.ConnectionFactory;

public class CommonTemplate {
	
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
			//Method[] methods=clazz.getMethods();
			//Method[] methods=clazz.getDeclaredMethods();
			/*
			for(int j=0;j<methods.length;j++){
				System.out.println(methods[j]);
			}
			*/
			rsmd=rs.getMetaData();
			int count=rsmd.getColumnCount();
			//System.out.println(count);
			String[] colFields=new String[count];
			for(int i=0;i<count;i++){
				//colFields[i]=rsmd.getColumnLabel(i+1);
				colFields[i]=rsmd.getColumnName(i+1);
				//System.out.println(colFields[i]);
			}
			if(rs.next()){	
				t=clazz.newInstance();
				System.out.println(t.getClass().getName());
				for(int i=0;i<count;i++)
				{
					Field field=clazz.getDeclaredField(colFields[i]);
					field.setAccessible(true);
					field.set(t, rs.getObject(colFields[i]));
					//String methodName="set"+colFields[i];
					/*
					//这种方式行不通，无法确定方法是否有重载。
					for(Method m:methods){
						if(m.getName().equals(methodName)){
							//System.out.println(t);
							System.out.println(m.getName()+":"+methodName+":"+colFields[i]+":"+rs.getObject(colFields[i]));
							m.invoke(t, new Object[]{rs.getObject(colFields[i])});
							break;	
						}
					}
					*/
					/*
					//这种方式也行不通，无法确定方法的参数类型。
					System.out.println(methodName);
					Method method=clazz.getMethod(methodName,new Class<?>[]{Object.class});
					method.invoke(t, new Object[]{rs.getObject(colFields[i])});
					*/
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
		}/* catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} */catch (NoSuchFieldException e) {
			e.printStackTrace();
		} 
		ConnectionFactory.free(rs, ps, conn);
		return t;
	}
}
