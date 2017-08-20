<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'BookInfoResult.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  <%
  	request.setCharacterEncoding("gb2312");
  	String book=request.getParameter("BookInfo");
  %>  	
  <%if(book.equals("大学英语")){%>
  <jsp:forward page="English.html"></jsp:forward>
  <%}if(book.equals("高等数学")){%>
  <jsp:forward page="AdvanceMath.html"></jsp:forward>
  <%}if(book.equals("C语言程序设计")){%>
  <jsp:forward page="C_language.html"></jsp:forward>
  <%}if(book.equals("Java程序设计")){%>
  <jsp:forward page="Java_language.html"></jsp:forward>
  <%}%>
  </body>
</html>
