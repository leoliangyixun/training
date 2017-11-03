<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<html>
  <head>
  </head>
  
  <body>
  <%
  	request.setCharacterEncoding("gb2312");
  	String book=request.getParameter("book");
  %>  	
  <%
  if(book.equals("大学英语"))
  {%>
  <jsp:forward page="Englishbook.html"></jsp:forward>
  <%}
  if(book.equals("高等数学"))
  {%>
  <jsp:forward page="Mathbook.html"></jsp:forward>
  <%}
  if(book.equals("C语言程序设计"))
  {%>
  <jsp:forward page="C-languagebook.html"></jsp:forward>
  <%}
  if(book.equals("Java程序设计"))
  {%>
  <jsp:forward page="Java-languagebook.html"></jsp:forward>
  <%}%>
  </body>
</html>
