<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <title>My JSP 'ConServerSQL.jsp' starting page</title>
  </head>
  <body>
   <%
   try{
	   //String url="jdbc:sqlserver://localhost:1433;DatabaseName=db_manpower";
	   String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=db_manpower";
	   String user="sa";
	   String password="root";
	   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	   Connection conn=DriverManager.getConnection(url,user,password);
	   out.println("数据库连接成功");
   }
   catch(ClassNotFoundException e){
	   out.print(e.getMessage());
	   out.println("数据库连接失败");
	   }
   %>
  </body>
</html>
