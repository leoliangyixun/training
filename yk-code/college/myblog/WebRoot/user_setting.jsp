<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>系统设置</title>
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
<li><a href="user_home.jsp">我的主页</a></li>
<li><a href="user_friends.jsp">好友管理</a></li>	
<li><a href="user_contacts.jsp">通讯录</a></li>
<li class="current_page_item"><a href="user_setting.jsp">博客设置</a></li>
</ul>
</div>

</div>
</div>
<div id="list">
<div id="list_top">
	<div id="list_middle">
		<div id="list_bottom">
			<div id="blog_list">
				<div id="link_list_style">
					<a href="javascript:showUserImageAlterArea()" >修改用户图像</a>
				    <a href="javascript:showBlogInfoAlterArea()" >修改博客信息</a>
					<a href="javascript:showUserInfoAlterArea()" >修改用户信息</a>
				</div>
				<script type="text/javascript" language="javascript">
				<!--
					javascript:showUserImageAlterArea();
				-->
				</script>
				<div id="user_info_setting"></div>
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

