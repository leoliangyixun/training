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
  <%if(book.equals("��ѧӢ��")){%>
  <jsp:forward page="English.html"></jsp:forward>
  <%}if(book.equals("�ߵ���ѧ")){%>
  <jsp:forward page="AdvanceMath.html"></jsp:forward>
  <%}if(book.equals("C���Գ������")){%>
  <jsp:forward page="C_language.html"></jsp:forward>
  <%}if(book.equals("Java�������")){%>
  <jsp:forward page="Java_language.html"></jsp:forward>
  <%}%>
  </body>
</html>
