<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'BooksResult.jsp' starting page</title>  
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
    	String[] book=request.getParameterValues("book");	
    %>
    你好：<a style="font-size:35px;color:#F00"><%=user%></a><br>
    你购买的图书为：<br>
    <%
    	int i;
    	for(i=0;i<book.length;i++) 
    	out.println("<a style=color:red;font-size:20px>"+book[i]+"</a>"+"<br>");
    %>  
  </body>
</html>
