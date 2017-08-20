<%@ page language="java" import="java.util.*" pageEncoding="GB2312"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.sql.ResultSet"%>
<jsp:useBean id="d" class="com.yangkai.bean.AjaxFriendsList" scope="session"></jsp:useBean>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    <script type="text/javascript" language="javascript">
    <!--
    function show(username)
    {
    	alert(username);
    }
    -->
    </script>
  </head>
  
  <body>
   <%
       ResultSet username_rs=null;
       String usernamestr=null;
       String userclass=d.setCharactorEncoding(request.getParameter("userclass"));
       out.println("userclass:"+userclass+"<br>");
       out.println("usernamestr:"+usernamestr+"<br>");
       if(userclass.equals("Default"))
       {
    	    
       		usernamestr="SELECT name FROM calldetails";
       }
       else
       {
    	   
       	    usernamestr="SELECT name FROM calldetails WHERE userclass='"+userclass+"'";
       }
       out.println("userclass:"+userclass+"<br>");
       out.println("usernamestr:"+usernamestr+"<br>");
       username_rs=d.myexecuteQuery(usernamestr);
    %>
       <form action="fuck.jsp" method="get">
    <%
       out.println("<select name=username><option value=Default>--—°‘Ò–’√˚--</option>");
	   while(username_rs.next())
	   {
	 		out.print("<option value="+username_rs.getString(1).toString()+">"+username_rs.getString(1).toString()+"</option>");  		
	   }  
	   out.println("</select>");
       username_rs.close();
    %>
    <input type="submit" value="Go">
    </form>
  </body>
</html>
