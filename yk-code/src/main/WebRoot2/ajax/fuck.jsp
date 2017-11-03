<%@ page language="java" import="java.util.*" pageEncoding="GB2312"%>
<jsp:useBean id="d" class="com.yangkai.bean.AjaxFriendsList" scope="session"></jsp:useBean>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>My JSP 'fuck.jsp' starting page</title>
  </head>
  
  <body>
    <%=d.setCharactorEncoding(request.getParameter("username"))%>
  </body>
</html>
