<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>

<title>枫雅博客：注册成功</title>
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
<%
String username=(String)session.getAttribute("username");
String password=(String)session.getAttribute("password");
%>
<div>
<div id="nav"><jsp:include page="regist_logo.jsp" flush="true"></jsp:include></div>
<div id="regist">
  <div id="list_top">
    <div id="list_middle">
      <div id="list_bottom">
 				<%
 				out.println("<div align='center'>");
 				out.println("<form name='loginForm' method='post'>");
 				out.println("<input type='hidden' name='username' value='"+username+"'/>");
 				out.println("<input type='hidden' name='password' value='"+password+"'/>");
 				out.println("<input type='hidden' name='login_mark' value='"+Constants.BLOG_LOGIN_MARK_FROM_INDEX+"'/>");
 				out.println("<a class='success_style'>恭喜你，注册成功。</a><a href='javascript:login()' class='return_style'>进入首页</a>");
 				out.println("</form>");
 				out.println("</div>");
 				session.removeAttribute("username");
 				session.removeAttribute("password");
				%>
      </div>
    </div>
  </div>
  
</div>
	<jsp:include page="bottom.html" flush="true"></jsp:include>
</div>
</body>
</html>

