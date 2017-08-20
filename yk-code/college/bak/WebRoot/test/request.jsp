<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<jsp:useBean id="people" class="test.Beanscope" scope="request"></jsp:useBean>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>My JSP 'request.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
  </head>
  
  <body>
    request作用域演示<br>
     现在时间是：<%=people.getDate() %><br>
     当前积分为：<%out.println(people.getNumber()); %>
  </body>
</html>
