package com.yangkai.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPool {
	DataSource ds=null;
	Context context=null;
	Connection conn=null;
	Statement stmt=null;
	public  ConnectionPool(){
		try {
			 context=(Context) new InitialContext().lookup("java:comp/env");
			 ds=(DataSource) context.lookup("jdbc/blog");
			 if(ds!=null)
			 {
				 try {
					conn=ds.getConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			 }
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		ConnectionPool pool=new ConnectionPool();
		if(pool.conn!=null)
		{
			 System.out.println("从连接池中获得一个连接。");
		}else{
			 System.out.println("无法从连接池中获得连接!");
		}

	}

}
