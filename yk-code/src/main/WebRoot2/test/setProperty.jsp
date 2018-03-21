<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="com.yangkai.javabean.StudentBean" scope="session"></jsp:useBean>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>My JSP 'SetProperty.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	

  </head>
  
  <body>
  <%
 user.setname("付翠");
  user.setcheng(85.0);
  
  %>
      姓名：<%=user.getname()%><br>
    成绩：<%=user.getcheng()%>分<br>
  <%!String user1="杨开"; %>
    <jsp:setProperty property="name" name="user" value="<%=user1 %>"/>
    <jsp:setProperty property="cheng" name="user" value="99.0"/>
    姓名：<%=user.getname()%><br>
    成绩：<%=user.getcheng()%>分<br>
    <hr>
    现在时间为：<%=new java.util.Date().toLocaleString() %>
  </body>
</html>
