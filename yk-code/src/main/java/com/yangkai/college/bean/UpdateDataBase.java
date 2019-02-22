package com.yangkai.bean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class UpdateDataBase 
{
	 ResultSet RS=null;
	 Statement stmt=null;
	 Connection conn=null;
	 int n;
	 String url="jdbc:mysql://localhost:3306/friends?user=root&password=root&useUnicode=true";
	public UpdateDataBase ()
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
	public void closeResultSet()
	{
		try {
			RS.close();
			System.out.println("ResultSet���ͷš�");
	} 
	catch (SQLException e) {
		e.printStackTrace();
		System.out.println("ResultSet�ͷ�ʧ�ܡ�");
	}
	}
	public void closeStatement()
	{
		try {
			stmt.close();
			System.out.println("Statement���ͷš�");
	} 
	catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Statement�ͷ�ʧ�ܡ�");
	}
	}
	public void closeConnection()
	{
		try {
			conn.close();
			System.out.println("Connection���ͷš�");
	} 
	catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Connection�ͷ�ʧ�ܡ�");
	}
	}
	public static void main(String[] args) throws NullPointerException
	{
		UpdateDataBase  d=new UpdateDataBase ();
	try
	{
		/*
		String sql="DELETE FROM callinfo WHERE  name='leo'";
		int num=d.myexecuteUpdate(sql);
		if(num>0)
			System.out.println("ɾ��ɹ�");
		else
			System.out.println("ɾ��ʧ��");
			*/
		String username="����";
		String tel="110";
		String sql="INSERT INTO callinfo VALUES('"+username+"','"+tel+"')";
		int num=d.myexecuteUpdate(sql);
		if(num>0)
			System.out.println("��ӳɹ�");
		else
			System.out.println("���ʧ��");
	}
	catch (Exception e) {
		e.getStackTrace();
	}
	d.closeStatement();
	d.closeConnection();
	}
}
