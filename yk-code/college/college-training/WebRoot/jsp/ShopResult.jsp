<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<html>
  <head>
  </head>
  
  <body>
    <%
    	request.setCharacterEncoding("gb2312");
    	String user=request.getParameter("user");
    	String password=request.getParameter("password");
    	String mybook=request.getParameter("mybook");
    %>
    你好:<%=user%><br>
    你购买的图书为:<%=mybook %>
  </body>
</html>
