package com.yangkai.bean;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class FriendsList 
{
	 ResultSet RS=null;
	 Statement stmt=null;
	 Connection conn=null;
	 int n;
	 String url="jdbc:mysql://localhost:3306/friends?user=root&password=root&useUnicode=true";
	public FriendsList()
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
	public ResultSet myexecuteQuery(String sql)  
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
	public int myexecuteUpdate(String sql)  
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
				RS.close();
				System.out.println("ResultSet���ͷš�");
		} 
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ResultSet�ͷ�ʧ�ܡ�");
		}
		try {
			stmt.close();
			System.out.println("Statement���ͷš�");
	} 
	catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Statement�ͷ�ʧ�ܡ�");
	}
		try {
			conn.close();
			System.out.println("Connection���ͷš�");
	} 
	catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Connection�ͷ�ʧ�ܡ�");
	}
	}
	public String setCharactorEncoding(String str) throws UnsupportedEncodingException
	{
		return new String(str.getBytes("ISO-8859-1"),"GBK");
	}
	/*
	public static void main(String[] args) throws NullPointerException
	{
	FriendsList d=new FriendsList();
	String sql="select * from callinfo";
	ResultSet rs=d.myexecuteQuery(sql);
	System.out.print("����");
	System.out.print("----------");
	System.out.println("TEL/QQ");
	System.out.println("**************************************");
	try {
		while(rs.next())
		{
			System.out.print(rs.getString(1));
			System.out.print("----------");
			System.out.println(rs.getString(2));
		}
		rs.close();
	} 
	catch (SQLException e) {
		e.printStackTrace();
		}
	if(d.RS!=null)
	{
		System.out.println("RSδ�ͷ�!!!!");
	}
	else{
		System.out.println("RS�ѳ�ʼ��Ϊnull");
	}
	if(rs!=null)
	{
		System.out.println("rsδ�ͷ�!!!!");
	}
	else{
		System.out.println("rs�ѳ�ʼ��Ϊnull");
	}
	d.closeConnection();
	
	if(d.RS!=null)
	{
		System.out.println("RSδ�ͷ�!!!!");
	}
	else{
		System.out.println("RS�ѳ�ʼ��Ϊnull");
	}
	if(rs!=null)
	{
		System.out.println("rsδ�ͷ�!!!!");
	}
	else{
		System.out.println("rs�ѳ�ʼ��Ϊnull");
	}
	}
	*/
}
