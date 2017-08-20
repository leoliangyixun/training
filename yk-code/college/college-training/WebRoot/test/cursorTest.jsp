<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import ="java.sql.Connection"%>
<%@ page import ="java.sql.DriverManager"%>
<%@ page import ="java.sql.ResultSet"%>
<%@ page import ="java.sql.SQLException"%>
<%@ page import ="java.sql.Statement"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'CursorTest.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
    <%!
    public class CursorTest 
    {
    	public ResultSet rs=null;
    	public Statement stmt=null;
    	public Connection conn=null;
    	public String URL="jdbc:mysql://localhost:3306/book?" +
    						"user=root&password=rootroot" +
    						"&useUnicode&characterEcoding=gb2312";
    public CursorTest ()
    {
    	try{	
    		Class.forName("com.mysql.jdbc.Driver");
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
    }
    %>
    
    <%
    CursorTest Record=new CursorTest();
   try{
   String sql="SELECT * FROM bookinfo ";
   ResultSet RS;
   int i;
   RS=Record.execute(sql);
   ResultSetMetaData RSM=RS.getMetaData();
   int count=RSM.getColumnCount();
   out.print("<center><table border=1>");
   out.print("<tr>");
	   for(i=1;i<=count;i++)
	   {out.print("<td  class=nav>"+RSM.getColumnName(i)+"</td>"); }
   out.print("</tr>");
   RS.next();
   while(!RS.isAfterLast())
   {
	 //  out.print(RS.getRow()+"<br>");
	   out.print("<tr>");
	   for(i=1;i<=count;i++)
	   {out.print("<td>"+RS.getString(i)+"</td>"); }
	   out.print("</tr>");
	   RS.next();
   }

   out.print("</table></center>");
   Record.closeConnection();
   }catch(SQLException e)
   {out.print(e.getMessage());}
   %>
  </body>
</html>
