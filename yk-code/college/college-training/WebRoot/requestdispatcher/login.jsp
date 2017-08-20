<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>My JSP 'login.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

  </head>
  
  <body>
    <form action="../RequestDispatcherServlet" method="post">
    <div>username:<input type="text" name="username"/></div>
    <div>password:<input type="text" name="password"/></div>
    <div><input type="submit" value="确定"/><input type="reset" value="取消"/></div>
    </form>
  </body>
</html>
