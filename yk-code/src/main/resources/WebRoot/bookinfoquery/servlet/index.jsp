<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
   <form action="../../bookinfoquery/QueryDisplay" method="post">
      <table border="0" align="center">
        <tr>
          <td>书名:</td>
          <td><input type="text" name="bookname" id="login"></td>  <td><input type="submit" value="查询"></td>
        </tr>
      </table>
    </form>
  </body>
</html>
