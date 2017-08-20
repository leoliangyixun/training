<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.sql.ResultSet"%>
<jsp:useBean id="d" class="com.yangkai.bean.AjaxFriendsList" scope="session"></jsp:useBean>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>My JSP 'ajaxfriendslistresult.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	

  </head>
  
  <body>
   <%
       ResultSet username_rs=null;
       String usernamestr=null;
       String userclass=request.getParameter("userclass");
       userclass=URLDecoder.decode(userclass, "UTF-8");
       out.println(userclass);
       if(userclass=="null")
       {
    	    //out.println("<script language='javascript'>alert('Are you kidding me!!!')</script>");
    	 	usernamestr="SELECT name FROM calldetails";
       }
       else
       {
       	usernamestr="SELECT name FROM calldetails WHERE userclass='"+userclass+"'";
       }
       out.println(usernamestr);
       username_rs=d.myexecuteQuery(usernamestr);
       out.println("<select><option name=username value=null>--选择姓名--</option>");//这是我的注释部分，是为了方便你们看的。
	 while(username_rs.next())
	 {
	 	out.print("<option value="+username_rs.getString(1).toString()+">"+username_rs.getString(1).toString()+"</option>");  		
	 }  
	 out.println("</select>");
       username_rs.close();
      %>
  </body>
</html>
