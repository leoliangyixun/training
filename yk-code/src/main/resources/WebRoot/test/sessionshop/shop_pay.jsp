<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    
    <title>My JSP 'shop_pay.jsp' starting page</title>

  </head>
  
  <body>
    <div align="center">
              �����������ι������Ʒ��<br>
     <% 
     ArrayList mylist=(ArrayList)session.getAttribute("mylist");
     
     out.println("<table border='0'>");
     for(int i=0;i<mylist.size();i++)
     {
    	 out.println("<tr>");
    	 out.println("<td>��Ʒ:"+(i+1)+"</td><td>"+(String)mylist.get(i)+"</td>");
    	 out.println("</tr>");
     }
     out.println("<table>");
     %>
    </div>
  </body>
</html>
