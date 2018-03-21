<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>

<title>枫雅博客：留言成功</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/myblog.js"></script>
<style type="text/css">
#regist {
	width:620px;
	margin:20px auto;
}
</style>
</head>

<body>
<div>
<div id="nav"><jsp:include page="message_logo.jsp" flush="true"></jsp:include></div>
<div id="regist">
  <div id="list_top">
    <div id="list_middle">
      <div id="list_bottom">
 				<%
 				out.println("<div align='center'>");
 				out.println("<a class='success_style'>留言成功。</a><a href='../my_leave_message.jsp' class='return_style'>查看我写的留言</a>");
 				out.println("</div>");
				%>
      </div>
    </div>
  </div>
  
</div>
	<jsp:include page="bottom.html" flush="true"></jsp:include>
</div>
</body>
</html>

