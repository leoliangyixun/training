<%@page import="java.sql.SQLException"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>My JSP 'pooldisplay.jsp' starting page</title>
  </head>
  
  <body>
   <%
    Connection conn=null;
	Statement stmt=null;
	ResultSet rs=null;
	DataSource ds=null;
	Context ctx=null;
	try{
		ctx=new InitialContext();
		ds=(DataSource)ctx.lookup("java:comp/env/jdbc/MySQLPOOL_book");
	}catch (NamingException e) {
		e.printStackTrace();
	}
	
	try{
		conn=ds.getConnection();
		stmt=conn.createStatement();
		rs=stmt.executeQuery("SELECT * FROM bookinfo");
}catch(SQLException e){
	e.printStackTrace();
}
	
	out.println("<div align=\"center\"><table border=\"1\" bordercolor=\"#000000\" cellspacing=\"0\" cellpadding=\"5\" style=\"border-collapse: collapse\">");
	out.print("<tr align=\"center\" bgcolor=\"#FF9999\"><td>ISBN</td><td>图书名称</td><td>出版社</td><td>图书单价</td><td>库存量</td></tr>");
	try {
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
		} catch (SQLException e) {
				e.printStackTrace();
		}
	out.println("</table></div>");
   %>
  </body>
</html>
