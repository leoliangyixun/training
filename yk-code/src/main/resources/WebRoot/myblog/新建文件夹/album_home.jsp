<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>相册中心</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/myblog.js"></script>
	</head>

<body>
<div>
<div id="nav"><jsp:include page="include/top.jsp" flush="true"></jsp:include></div>
<div id="content">
<div id="menu_up">
<div id="menu">
<div>
<ul>	
<li><a href="user_album.jsp">我的相册</a></li>	
<li><a href="photo_upload.jsp">上传照片</a></li>
<li><a href="album_create.jsp">创建相册</a></li>	
<li class="current_page_item"><a href="album_home.jsp">相册中心</a></li>
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
				//out.println("<div  align='center'><a class='success_style'>你还没有相册!!!</a><a href='album_create.jsp' class='continue_style'>创建相册<a></div>"); 
			%>
			</div>
		</div>
	</div>
</div>
</div>
<div style="clear:both"></div>
</div>
<jsp:include page="include/bottom.html" flush="true"></jsp:include>
</div>
</body>
</html>

