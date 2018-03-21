<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'myjsptest.jsp' starting page</title>
    

  </head>
  
  <body>
   <%String s="This is a String"; %>
   <jsp:forward page="jspparam.jsp">
   <jsp:param name="hellostr" value="<%=s%>"/>
   </jsp:forward>
  </body>
</html>
