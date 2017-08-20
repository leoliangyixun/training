<%@ page language="java" import="java.util.*" pageEncoding="GB2312"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.sql.ResultSet"%>
<jsp:useBean id="d" class="com.yangkai.bean.AjaxFriendsList" scope="session"></jsp:useBean>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
  </head>
  
  <body>
   <%
       ResultSet username_rs=null;
       String usernamestr=null;
       String userclass=request.getParameter("userclass");
       userclass=URLDecoder.decode(userclass, "UTF-8");
       if(userclass.equals("Default"))
       {
       		usernamestr="SELECT name FROM calldetails";
       }
       else
       {
       	    usernamestr="SELECT name FROM calldetails WHERE userclass='"+userclass+"'";
       }
       username_rs=d.myexecuteQuery(usernamestr);
       out.println("<select name=username><option value=Default>--Ñ¡ÔñĞÕÃû--</option>");
	   while(username_rs.next())
	   {
	 		out.print("<option value="+username_rs.getString(1).toString()+">"+username_rs.getString(1).toString()+"</option>");  		
	   }  
	   out.println("</select>");
       username_rs.close();
       //d.closeConnection();
    %>
  </body>
</html>
