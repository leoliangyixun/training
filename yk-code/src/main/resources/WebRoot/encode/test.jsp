<%@page import="java.net.URLEncoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
  </head>
  
  <body>
   <form action="result.jsp" method="get">
   <input type="text" name="username"> 
   <input type="submit">
   </form>
   <%
   String username="杨开";
   out.println("<a href='result.jsp?username="+URLEncoder.encode(username,"GBK")+"'>传递参数1</a>");
   out.println("<a href='result.jsp?username="+username+"'>传递参数2</a>");
   out.println("<a href='result.jsp?username="+URLEncoder.encode(username,"UTF-8")+"'>传递参数3</a>");
   %>
  </body>
</html>
