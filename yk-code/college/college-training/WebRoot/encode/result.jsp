<%@page import="org.yangkai.test.tools.GBKEncodingTool"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>My JSP 'result.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

  </head>
  
  <body>
   <% 
   String username=request.getParameter("username");
   out.println("默认："+username+"<br/>");
   String username1=URLDecoder.decode(username, "GBK");
   out.println("URLDecoder.decode："+username1+"<br/>");
   String username2=GBKEncodingTool.setCharacterEncoding(username);
   out.println("GBKEncodingTool："+username2+"<br/>");
   String username3=new String(username.getBytes("ISO-8859-1"),"GBK");
   out.println("new String()："+username3+"<br/>");
   //String username4=URLDecoder.decode(username, "UTF-8");
    String username4=new String(username.getBytes("ISO-8859-1"),"UTF-8");
   out.println("URLDecoder.decode："+username4+"<br/>");
   %>
  </body>
</html>
