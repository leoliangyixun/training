<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>My JSP 'JExample.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  
   <%
   String name=request.getParameter("name");
   name=new String(name.getBytes("iso-8859-1"));
   String sex=request.getParameter("sex");
   sex=new String(sex.getBytes("iso-8859-1"));
   String fruit[]=request.getParameterValues("fruit");
   if(sex.equals("女"))
   		{out.println("欢迎你，"+name+"女士");}
   else
   		{out.println("欢迎你，"+name+"先生");}
   
   %> 
  </body>
</html>
