package com.yangkai.bean;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class PagesApartMySQL 
{
	 ResultSet RS=null;
	 Statement stmt=null;
	 Connection conn=null;
	 int n;
	 String url="jdbc:mysql://localhost:3306/friends?user=root&password=root&useUnicode=true";
	public PagesApartMySQL()
	{
		 try
		 {	 
			 Class.forName("com.mysql.jdbc.Driver");
			 conn=DriverManager.getConnection(url);
			 stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		 }
		 catch (ClassNotFoundException e)  {
			 e.printStackTrace();
		 } 
		 catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ResultSet executeQuery(String sql)  
	{
		try
		{	 
			 RS=stmt.executeQuery(sql);
		}
		catch(SQLException e)
		{
			System.err.print(e.getMessage());
		} 
		return RS;	
	}
	public int executeUpdate(String sql)  
	{
		try
		{	 
			 n=stmt.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			System.err.print(e.getMessage());
		} 
		return n;
	}
	public void closeConnection()
	{	
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
	}
	public String setCharactorEncoding(String str) throws UnsupportedEncodingException
	{
		return new String(str.getBytes("ISO-8859-1"),"GBK");
	}
}
