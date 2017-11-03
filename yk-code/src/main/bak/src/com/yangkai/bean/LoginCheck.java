package com.yangkai.bean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class LoginCheck
{
	public Boolean check=false;
	public ResultSet rs=null;
	public Statement stmt=null;
	public Connection conn=null;
	public String URL="jdbc:mysql://localhost:3306/book?" +
						"user=root&password=rootroot" +
						"&useUnicode&characterEcoding=gb2312";
public LoginCheck()
{
	try{	
		Class.forName("org.gjt.mm.mysql.Driver");
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
/*public ResultSet execute(String sql)  
{
	try{
		conn=DriverManager.getConnection(URL);
		stmt=conn.createStatement();
		rs=stmt.executeQuery(sql);
		}catch(SQLException e){
		System.err.print(e.getMessage());
		} 
	finally{}
	return rs;	
}*/
public Boolean checkManager(String username)
{
	try{
		
		conn=DriverManager.getConnection(URL);
		stmt=conn.createStatement();
		//String sql ="SELECT * FROM loginuser WHERE name='"+username+"'";
		rs=stmt.executeQuery("SELECT * FROM loginuser WHERE name='"+username+"'");
		if (rs.next())
			check=true;
		}catch(SQLException e){
		System.err.print(e.getMessage());
		} 
	
	return check;
	//finally{}
	//return rs;	
	//LoginCheck logincheck=new LoginCheck();
	
	//ResultSet RS=logincheck.execute(sql);
	
}
}
