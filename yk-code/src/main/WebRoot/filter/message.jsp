<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'worlddisplay.jsp' starting page</title>
    <style type="text/css">
    #nav{}
    #nav td{ border:#000 dashed 1px;}
    </style>
  </head>
  
  <body>
 <%
 String username=(String)request.getAttribute("username");
 String content=(String)request.getAttribute("content");
 %>
 <div align="center" id="nav">
  <table>
    <tr>
      <td>留言人：</td>
      <td><input type="text" name="username" value="<%=username%>"/></td>
    </tr>
    <tr>
      <td>留言内容：</td>
      <td><textarea name="content"  cols="45" rows="5" ><%=content%></textarea></td>
    </tr>
  </table>

</div>
  </body>
</html>
