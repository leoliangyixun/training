<%@ page language="java" import="java.util.*,java.sql.*,java.io.*" pageEncoding="gb2312"%>
<jsp:useBean id="Record" class="com.yangkai.bean.ResultSetRecord" scope="page"></jsp:useBean>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'ResultSetRecord.jsp' starting page</title>
    <style type="text/css">
    .nav{color:red;font-size: 36px;}
    </style>
  </head>
  
  <body>
   <%
   try{
   String sql="SELECT * FROM bookinfo ";
   ResultSet RS;
   int i;
   RS=Record.execute(sql);
   ResultSetMetaData RSM=RS.getMetaData();
   int count=RSM.getColumnCount();
   out.print("<center><table border=1>");
   out.print("<tr>");
	   for(i=1;i<=count;i++)
	   {out.print("<td  class=nav>"+RSM.getColumnName(i)+"</td>"); }
   out.print("</tr>");
   while(RS.next())
   {
	   out.print("<tr>");
	   for(i=1;i<=count;i++)
	   {out.print("<td>"+RS.getString(i)+"</td>"); }
	   out.print("</tr>");
	   //RS.next();
   }
   
   out.print("</table></center>");
   Record.closeConnection();
   }catch(SQLException e)
   {out.print(e.getMessage());}
   %>
  </body>
</html>

