<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>My JSP 'requestsetAttribute2.jsp' starting page</title>
  

  </head>
  
  <body>
   <%    
   Integer id=(Integer)request.getAttribute("id");
   out.println(id); 
   %>
  </body>
</html>
