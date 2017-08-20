<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page language="java" import="java.util.*" pageEncoding="gb2312"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    
    <title>My JSP 'display.jsp' starting page</title>
    
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
  <%
    Connection conn=null;
	Statement stmt=null;
	ResultSet rs=null;
	DataSource ds=null;
	ds=(DataSource)application.getAttribute("DS");
	
	try{
	   conn=ds.getConnection();
	   stmt=conn.createStatement();
	   rs=stmt.executeQuery("SELECT * FROM bookinfo");
	}catch(SQLException e){
		e.printStackTrace();
	}
		
	/*
	if(conn==null)
		out.println("conn为空");
	if(stmt==null)
		out.println("stmt为空");
	if(rs==null)
		out.println("rs为空");
	if(ds==null)
		out.println("ds为空");
	else
	    out.println(ds);
	*/
	
	
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
