package com.yangkai.bean;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class PagesApartMSSQLServer 
{
	 ResultSet RS=null;
	 Statement stmt=null;
	 Connection conn=null;
	 String user="sa";
	 String password="root";
	 String URL="jdbc:sqlserver://localhost:1433;databaseName=book";
public PagesApartMSSQLServer ()
{
	try{	
		 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}catch (ClassNotFoundException e) 
		{e.getMessage();}
}
public void closeConnection()	
{
	try {	
		RS.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}	
}
public String setCharacterEncoding(String str) throws UnsupportedEncodingException
{
	return new String(str.getBytes("ISO-8859-1"),"GBK");
}
public ResultSet execute(String sql)  
{
	try{
		conn=DriverManager.getConnection(URL,user,password);
		stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	    RS=stmt.executeQuery(sql);
		}catch(SQLException e){
			System.err.print(e.getMessage());
		} 
	return RS;
}
}