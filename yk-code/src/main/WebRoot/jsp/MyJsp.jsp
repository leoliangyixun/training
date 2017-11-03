<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	

  </head>
  
  <body>
  
   <form>
   <input type="text" name="num"/>
   <input type="submit" value="计算平方"/>
   </form>
   <%
   String str=request.getParameter("num"); 
   if(str==null)
	  out.print("");
   else
	   {
		   int num=Integer.parseInt(str);
		   out.print(num+"的平方为：");
		   num=num*num;
		   out.print(num);
	   }
   %>
  </body>
</html>
