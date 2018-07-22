<%@page import="sun.nio.cs.ISO_8859_2"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'BookResult.jsp' starting page</title> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
    <%
    	request.setCharacterEncoding("gb2312");
    	String user=request.getParameter("user");
    	String password=request.getParameter("password");
    	String book=request.getParameter("book");
    %>
    你好：<a style="font-size:20px;color:#F00"><%=user%></a><br>
    你购买的图书为：<a style="font-size:20px;color:#F00"><%=book %></a>
  </body>
</html>
