<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'PrintHello.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  <form>
   请输入数字：<input type="text" name="num"/>
   <input type="submit" value="打印"/>
   </form>
   <%
   String s=request.getParameter("num"); 
   if(s!=null)
   {
		int num=Integer.parseInt(s);
		for(int i=1;i<=num;i++)
			if(i<10)
	    		out.print("0"+i+"Hello World"+"<br>");
			else
				out.print(i+"Hello World"+"<br>");
   }
   %>
  </body>
</html>
