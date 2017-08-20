package com.training.dao.college.dao.impl2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yangkai.dao.factory.ConnectionFactory;

interface Callback<T>{
	public T doInCallback(Connection conn,PreparedStatement ps,ResultSet rs);
}
public abstract class AbstractJdbcTemplate {
	
	public <T> T query(final String sql,final Object[] args,final Class<T> clazz){
	
		return this.execute(new Callback<T>() {

			@Override
			public T doInCallback(Connection conn,PreparedStatement ps, ResultSet rs) {
				try {
					ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					for(int i=0;i<args.length;i++){
						ps.setObject(i+1, args[i]);
					}
					rs=ps.executeQuery();
					while(rs.next()){
						clazz.newInstance();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}
	//ʹ�������ڲ��ࡣ
	public <T> T execute(Callback<T> callback){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		conn=ConnectionFactory.getConnection();
		T t=callback.doInCallback(conn,ps,rs);
		ConnectionFactory.free(rs, ps, conn);
		return t;
	}
	//ʹ�ó��󷽷���
	public <T> T execute(){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		conn=ConnectionFactory.getConnection();
		T t=this.doInCallback(conn, ps, rs);
		ConnectionFactory.free(rs, ps, conn);
		return t;
	}
	public abstract <T> T doInCallback(Connection conn,PreparedStatement ps,ResultSet rs);
}
