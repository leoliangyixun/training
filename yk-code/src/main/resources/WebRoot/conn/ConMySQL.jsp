<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
    <title>My JSP 'ConMySQL.jsp' starting page</title>
  </head>
  
  <body>
   <%
   try{
   String user="root";
   String password="rootroot";
   String database="book";
   Class.forName("com.mysql.jdbc.Driver");
 //Class.forName("org.mysql.mm.jdbc.Driver");
   String url="jdbc:mysql://127.0.0.1:3306/"+database+"?user="+user+"&password="+password+"&useUnicode=true&characterEncoding=gb2312";
 //String url="jdbc:mysql://localhost:3306/book?user=root&password=rootroo&useUnicode";
   Connection conn=DriverManager.getConnection(url);
   out.println("数据库连接成功");
   conn.close();
   }catch(SQLException e){ 
	   //out.println(e.getStackTrace()+"<br>");
	  // out.println("数据库连接失败");
	   //e.getStackTrace();
	   }
   %>
  </body>
</html>
