<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'test.jsp' starting page</title>
    
  </head>
  
  <body>
   <%-- 
   String name=(String)application.getAttribute("name");
   out.println(name);
   --%>
   <%
   String name="";
   application.setAttribute("name", "�");
   application.setAttribute("name", "����");
   application.setAttribute("name", "����");
   application.setAttribute("name", "����");
   application.removeAttribute("name");
   
   %>
  </body>
</html>
