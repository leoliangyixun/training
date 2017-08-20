<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ConnMySQLDriver.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <%
   try{
   String user="root";
   String password="rootroot";
   String database="book";
   String url="jdbc:mysql://127.0.0.1:3306/"+database+"?user="+user+"&password="+password+"&useUnicode=true&characterEncoding=gb2312";
   Class.forName("org.gjt.mm.mysql.Driver");
   Connection conn=DriverManager.getConnection(url);
   out.println("数据库连接成功");
   conn.close();
   }catch(SQLException e){ 
	   //out.println(e.getStackTrace()+"<br>");
	     out.println("数据库连接失败");
	   //e.getStackTrace();
	   }
   %>
  </body>
</html>
