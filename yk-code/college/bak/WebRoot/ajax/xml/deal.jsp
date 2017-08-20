<%@page import="java.net.URLDecoder"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.yangkai.bean.AjaxDBConection"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>My JSP 'deal.jsp' starting page</title>
  </head>
  
  <body>
   <%
   	ResultSet rs=null;
   	AjaxDBConection ac=new AjaxDBConection();
   	String name=request.getParameter("name");
   	name=URLDecoder.decode(name,"UTF-8");
   	String str="SELECT * FROM bookinfo WHERE name LIKE '%"+name+"%'";
   	rs=ac.myexecuteQuery(str);
   	out.println("<div align=\"center\"><table border=\"1\" bordercolor=\"#000000\" cellspacing=\"0\" cellpadding=\"5\" style=\"border-collapse: collapse\">");
    out.print("<tr align=\"center\" bgcolor=\"#FF9999\"><td>ISBN</td><td>图书名称</td><td>出版社</td><td>图书单价</td><td>库存量</td></tr>");
   	while(rs.next())
   	{
   		out.println("<tr style=\"font-size:12px\">");
   		out.println("<td>"+rs.getString("ISBN")+"</td>");
   		out.println("<td>"+rs.getString("name")+"</td>");
   		out.println("<td>"+rs.getString("publisher")+"</td>");
   		out.println("<td>"+rs.getFloat("price")+"</td>");
   		out.println("<td>"+rs.getInt("num")+"</td>");
   		out.println("</tr>");
   	}
   	out.println("</table></div>");
   %>
  </body>
</html>
