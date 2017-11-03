<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    
    <title>My JSP 'jsParameter.jsp' starting page</title>
    <script type="text/javascript">
    <!--
    function send(username,password)
    {
    	alert(username);
    	alert(password);
    }
    function show(num1,num2)
    {
    	alert(num1);
    	alert(num2);
    	send("fuck","fuck");
    }
    -->
    </script>


  </head>
  
  <body>
  <%
 // String username=request.getParameter("username");
  //String password=request.getParameter("password");
  int num1=16;
  int num2=88;
  String username="yangkai";
  String password="fuck";
  %>
  <a href="javascript:send(<%=username%>,<%=password%>)">传递参数</a>
   <a href="javascript:send()">传递参数</a>
  <a href="javascript:show(<%=num1%>,<%=num2%>)">fuck</a>
  <%
  out.println("<a href='javascript:send("+username+","+password+")'>传递参数</a>");
  %>
  </body>
</html>
