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
  if(book.equals("��ѧӢ��"))
  {%>
  <jsp:forward page="Englishbook.html"></jsp:forward>
  <%}
  if(book.equals("�ߵ���ѧ"))
  {%>
  <jsp:forward page="Mathbook.html"></jsp:forward>
  <%}
  if(book.equals("C���Գ������"))
  {%>
  <jsp:forward page="C-languagebook.html"></jsp:forward>
  <%}
  if(book.equals("Java�������"))
  {%>
  <jsp:forward page="Java-languagebook.html"></jsp:forward>
  <%}%>
  </body>
</html>
