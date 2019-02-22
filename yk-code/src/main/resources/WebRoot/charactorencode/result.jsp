<%@ page language="java" import="java.util.*" pageEncoding="GB2312"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

  </head>
  
  <body>
<%
//request.setCharacterEncoding("UTF-8");
	out.println(request.getCharacterEncoding());
	String username=request.getParameter("username");
	out.println(username);
%>
  </body>
</html>
