<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title></title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/myblog.js"></script>
	</head>

<body>
<%
	String currpage=request.getParameter("page");
	String currstep=request.getParameter("step");
	request.setAttribute("word", "shit");
	//System.out.println("message_list_flush.jsp");
	//System.out.println("currpage:"+currpage);
	//System.out.println("currstep:"+currstep);
	//out.println("<script>manageMessageBox("+currpage+","+currstep+")</script>");//行不通
%>
<!--行不通-->
<script language="javascript" type="text/javascript">
manageMessageBox(<%=currpage%>,<%=currstep%>);
</script>
<div id="message"></div>
</body>
</html>

