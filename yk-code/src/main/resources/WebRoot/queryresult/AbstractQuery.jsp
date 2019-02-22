<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:useBean id="Query" class="com.yangkai.bean.AbstractQuery" scope="page"></jsp:useBean>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'AbstractQuery.jsp' starting page</title>
 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <form method="post">
 <table width="402"  align="center">
  <tr>
    <td colspan="2" align="center">模糊查询</td>
  </tr>
  <tr>
    <td width="160">请输入要查询的书名：</td>
    <td width="168"><input type="text" name="book" /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>
    <input type="submit" value="提交" />
    <input type="reset" value="重置" /></td>
  </tr>
</table>
  
  </form>
   <%
   request.setCharacterEncoding("utf-8"); 
   ResultSet RS=null;
   String bookname=request.getParameter("book");
   if (bookname==null)
	   	out.print("");
   else{
	   	 RS=Query.checkBookname(bookname);
	     ResultSetMetaData RSM=RS.getMetaData();
	     if(RS!=null&&RS.next())
		   	 	 {
	    	 		 RS.previous();
					 out.print("<center><table border=1>");
				  	 out.print("<tr><td>"+RSM.getColumnName(2)+"</td>"+"<td>"+RSM.getColumnName(3)+"</td></tr>");
				  	 while(RS.next())
				  	 out.print("<tr><td>"+RS.getString(2)+"</td>"+"<td>"+RS.getString(3)+"</td></tr>");
				  	 out.print("</table></center>");
				 	 Query.closeConnection();
			     }    
   		else{
   				out.print("<center>没有相关书籍</center>");
   			  }
   		}
   %>
  </body>
</html>
