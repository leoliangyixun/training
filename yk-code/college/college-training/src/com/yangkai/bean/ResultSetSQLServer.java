package com.yangkai.bean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class ResultSetSQLServer 
{
	 ResultSet rs=null;
	 Statement stmt=null;
	 Connection conn=null;
	 String user="sa";
	 String password="rootroot";
	 String URL="jdbc:sqlserver://localhost:1433;databaseName=book";
public ResultSetSQLServer ()
{
	try{	
		 Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
		}catch (ClassNotFoundException e) 
		{e.getMessage();}
}
public void closeConnection()	
{
	try {
		conn.close();
		stmt.close();
		rs.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}	
}
public ResultSet execute(String sql)  
{
	try{
		conn=DriverManager.getConnection(URL,user,password);
		stmt=conn.createStatement();
		rs=stmt.executeQuery(sql);
		}catch(SQLException e){
		System.err.print(e.getMessage());
		} 
	return rs;	
}
}
