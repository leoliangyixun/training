package com.yangkai.bean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class AbstractQuery
{
	public Boolean check=false;
	public ResultSet rs=null;
	public Statement stmt=null;
	public Connection conn=null;
	public String URL="jdbc:mysql://localhost:3306/book?" +
						"user=root&password=rootroot" +
						"&useUnicode&characterEcoding=gb2312";
public AbstractQuery()
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
public ResultSet execute(String sql)  
{
	try{
		conn=DriverManager.getConnection(URL);
		stmt=conn.createStatement();
		rs=stmt.executeQuery(sql);
		}catch(SQLException e){
		System.err.print(e.getMessage());
		} 
	return rs;	
}
public ResultSet checkBookname(String bookname)
{
		String sql ="SELECT * FROM bookinfo WHERE ���� like '%"+bookname+"%'";
		ResultSet rs_1=this.execute(sql);		
	    return rs_1;	
}
}