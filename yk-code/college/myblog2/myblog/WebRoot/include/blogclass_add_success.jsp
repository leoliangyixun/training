<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>添加成功</title>
	<link href="../css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="../js/myblog.js"></script>
	</head>

<body>

<div>
<div id="nav"><jsp:include page="top.jsp" flush="true"></jsp:include></div>
<div id="content">
<div id="menu_up">
<div id="menu">
<div>
	<ul>
	<li><a href="user_blog.jsp">博客列表</a></li>
	<li class="current_page_item"><a href="blogclass_add.jsp">新建分类</a></li>	
	<li><a href="blog_add.jsp">写博客</a></li>
	<li><a href="blog_manage.jsp">草稿箱</a></li>		
	</ul>
</div>
</div>
</div>
<div id="list">
<div id="list_top">
	<div id="list_middle">
		<div id="list_bottom">
			<div id="blog_list">
			<%
				
				out.println("<div align='center'><a class='success_style'>添加成功</a><a href='../blog_add.jsp' class='continue_style'>发表博文</a></div>");
				
				
			%>
			</div>
		</div>
	</div>
</div>
</div>
<div style="clear:both"></div>
</div>
<jsp:include page="bottom.html" flush="true"></jsp:include>
</div>
</body>
</html>

