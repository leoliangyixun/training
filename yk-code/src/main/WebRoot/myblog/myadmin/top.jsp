<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>My JSP 'index.jsp' starting page</title>
	<link href="../css/style.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
	#menu_style{
	margin-left:150px;
	}
	#welcome {
	margin-top: 20px;
	} 
  
	#welcome a{
	margin-right: 20px;
	font-size: 14px;
	color: black;
	}  
	</style>
	</head>

<body>
<%
	String loginuser = (String) session.getAttribute("loginuser");	
%>
<div>
<div id="nav">
<div id="top_wrapper">
	<div id="top_header">
		<div id="top_logo">
			<h1><a>一个人的安静</a></h1>
            <p><a>似水流年，只为伊人。</a></p>
		</div>
		<div id="top_search">
	      <div id="welcome"><div>欢迎你:<a href="#"><%=loginuser%></a><a href="LoginOut">安全退出</a></div></div>
	    </div>
    </div>
	<div id="menu_style">
		<div id="top_menu">
			<ul>
				<li><a href="index.jsp">首页</a></li>
				<li><a href="user_blog.jsp">博客</a></li>
				<li><a href="user_album.jsp">相册</a></li>
				<li><a href="user_mood.jsp">说说</a></li>
			</ul>
		</div>
	</div>
</div>
</div>
</div>
</body>
</html>

