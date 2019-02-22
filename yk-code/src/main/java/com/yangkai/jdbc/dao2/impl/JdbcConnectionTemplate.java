package org.yangkai.jdbc.dao2.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.yangkai.jdbc.dao2.Callback;
import org.yangkai.jdbc.factory.ConnectionFactory;
/*
 * 这个类仅仅只是做了获取连接，关闭连接的事情，具体的实现在回调方法中处理，而回调方法的真正实现交给子类,
 * 这样的模板在实际开发中不会用到。
 * */
public abstract class JdbcConnectionTemplate {
	
	public <T> T executeCallback(Callback<T> callback)
	{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		conn=ConnectionFactory.getConnection();
		//回调方法。
		T t=callback.doInCallback(conn,ps,rs);
		ConnectionFactory.free(rs, ps, conn);
		return t;
	}
}
