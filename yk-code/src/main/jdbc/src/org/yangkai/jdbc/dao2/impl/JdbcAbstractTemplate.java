package org.yangkai.jdbc.dao2.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import org.yangkai.jdbc.dao2.RowMapper;
import org.yangkai.jdbc.factory.ConnectionFactory;

public abstract class JdbcAbstractTemplate {
	
	public <T> T find(Class<T> clazz,String sql,Object[] args,RowMapper<T> mapper)
	{
		return this.executeCallback(sql,args,mapper);
	}
	
	public <T> List<T> findAll(Class<T> clazz,String sql,Object[] args,RowMapper<List<T>> mapper)
	{
		return this.executeCallback(sql, args, mapper);
		//return this.executeCallbackList(sql, args, mapper);
	}
	/*
	 *�����T����ָ������Ҳ��ָ�����ϣ���ȡ���ڴ�������T��ʲô��ʽ��
	 *�����������T��ʾ�����򷵻ص�TҲ�ʹ�����������������T�����ϣ��ⷵ�ص�T�ʹ����ϣ�
	 *Ҳ����˵T���Դ����������ͣ����������ڶ���
	 * */
	public <T> T executeCallback(String sql,Object[] args,RowMapper<T> mapper)
	{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		conn=ConnectionFactory.getConnection();
		//System.out.println(conn);
		try {
			ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			for(int i=0;i<args.length;i++){
				ps.setObject(i+1, args[i]);
			}
			//ֻ��ִ�в�ѯ����������Ѿ�д���ˣ���̫��
			rs=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		T t=mapper.doInResultSet(rs);
		ConnectionFactory.free(rs, ps, conn);
		return t;
	}
	//���෽����
	public <T> List<T> executeCallbackList(String sql,Object[] args,RowMapper<List<T>> mapper)
	{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		conn=ConnectionFactory.getConnection();
		try {
			ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			for(int i=0;i<args.length;i++){
				ps.setObject(i+1, args[i]);
			}
			rs=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<T> t=mapper.doInResultSet(rs);
		ConnectionFactory.free(rs, ps, conn);
		return t;
	}
	
}
