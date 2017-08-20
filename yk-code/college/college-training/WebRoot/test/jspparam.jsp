<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'jspparam.jsp' starting page</title>


  </head>
  <body>
 <center>
  <%String str=request.getParameter("hellostr"); %>
  
   <h3>你传递的字符串是：<%=str%></h3> <br>
   </center>
  </body>
</html>
