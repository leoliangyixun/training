<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>My JSP 'index.jsp' starting page</title>
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
<li class="current_page_item"><a href="index.jsp">���²���</a></li>
<li><a href="friends_blog.jsp">���Ѷ�̬</a></li>
<li><a href="user_blog.jsp">����</a></li>			
<li><a href="user_album.jsp">���</a></li>
<li><a href="user_mood.jsp">˵˵</a></li>
<li><a href="user_msg.jsp">����</a></li>
<li><a href="user_home.jsp">��������</a></li>
</ul>
</div>
<div id="search">
<form method="get" action="#">
<div>
	<input type="text" name="keyword" id="search-text" value="����ؼ���" /> 
	<input type="submit" id="search-submit" value="GO" />		
</div>
</form>
</div>
</div>
</div>
<div id="list">
<div id="list_top">
	<div id="list_middle">
		<div id="list_bottom">
			<div id="blog_list">
			
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
