package com.yangkai.jdbc.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DataSourceDemo {

	public final static String DRIVER="com.mysql.jdbc.Driver";
	public final static String USER="root";
	public final static String PASSWORD="root";
	public final static String URL="jdbc:mysql://localhost:3306/myblog";
	private LinkedList<Connection> pool=new LinkedList<Connection>();
	
	public DataSourceDemo() 
	{
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		    Connection conn = null;
			for(int i=0;i<10;i++)
			{
				try {
					conn = DriverManager.getConnection(URL,USER,PASSWORD);
				} catch (SQLException e) {
					throw new ExceptionInInitializerError();
				}
				pool.addLast(conn);
			}
	}

	public Connection getConection()
	{
		return pool.removeFirst();
	
	}
	public static void main(String[] args)
	{


	}

}
