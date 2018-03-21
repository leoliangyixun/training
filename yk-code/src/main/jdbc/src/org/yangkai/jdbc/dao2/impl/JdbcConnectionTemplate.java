package org.yangkai.jdbc.dao2.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.yangkai.jdbc.dao2.Callback;
import org.yangkai.jdbc.factory.ConnectionFactory;
/*
 * ��������ֻ�����˻�ȡ���ӣ��ر����ӵ����飬�����ʵ���ڻص������д������ص�����������ʵ�ֽ�������,
 * ������ģ����ʵ�ʿ����в����õ���
 * */
public abstract class JdbcConnectionTemplate {
	
	public <T> T executeCallback(Callback<T> callback)
	{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		conn=ConnectionFactory.getConnection();
		//�ص�������
		T t=callback.doInCallback(conn,ps,rs);
		ConnectionFactory.free(rs, ps, conn);
		return t;
	}
}
