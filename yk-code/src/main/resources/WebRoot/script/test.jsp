<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript" language="javascript">
	<!--
	function show(i,j){
		alert("i="+i);
		alert("j="+j);
	}
	-->
	</script>

  </head>
  
  <body>
    <a href="javascript:show(1,2)">两个参数</a></br>
    <a href="javascript:show(1)">一个参数</a></br>
    <a href="javascript:show(1,)">一个参数与NULL</a></br><!--有错误-->
    <a href="javascript:show(1,'')">一个参数与空参数</a></br>
    <a href="javascript:show(1,null)">一个参数与null</a></br>
    <hr>
    <%
	    int i=1,j=2;
    	out.println("<a href='javascript:show("+i+","+j+")'>两个参数</a></br>");
    	out.println("<a href='javascript:show("+i+")'>一个参数</a></br>");
    	out.println("<a href='javascript:show("+i+",)'>一个参数与NULL</a></br>");
    	out.println("<a href='javascript:show("+i+",'')'>一个参数与空参数</a></br>");
    	out.println("<a href='javascript:show("+i+","+null+")'>一个参数与null</a></br>");
    	
    %>
  </body>
</html>
