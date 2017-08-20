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
       String userclass=d.setCharactorEncoding(request.getParameter("userclass"));
      
       if(userclass==null || userclass.equals(""))
       {
    	    usernamestr="SELECT name FROM calldetails";
       }
       else{
	          usernamestr="SELECT name FROM calldetails WHERE userclass='"+userclass+"'";
           }
	 username_rs=d.myexecuteQuery(usernamestr);
	 if(username_rs.isBeforeFirst()==username_rs.isAfterLast())
	 {
	    	out.println("Ã»ÓÐ¼ÇÂ¼");
	 }
	 else{
		 out.println("<select name=username>");
		 while(username_rs.next())
		 {
		       out.print("<option value="+username_rs.getString(1).toString()+">"+username_rs.getString(1).toString()+"</option>");  		
		 }
		 out.println("</select>");
	     }    
       username_rs.close();
      %>
  </body>
</html>
