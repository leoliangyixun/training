<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>My JSP 'Pages.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
<form action="" method="post">
<a href="Pages.jsp?page=<%=1 %>">第一页</a>
<a href="Pages.jsp?page=<%=2 %>">上一页</a>
<a href="Pages.jsp?page=<%=3 %>">下一页</a>
<a href="Pages.jsp?page=<%=4 %>">最后页</a>
</form>
<%

String p=request.getParameter("page");
if(p==null)
	out.print("");
else{
	int n=Integer.parseInt(p);
	out.print(n);
}

%>
  </body>
</html>
