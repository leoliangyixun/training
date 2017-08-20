<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>枫雅博客:上传成功</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/myblog.js"></script>
	</head>

<body>
<%
String album_name=URLDecoder.decode(request.getParameter("album_name"), "UTF-8");
int photo_num=Integer.parseInt(request.getParameter("photo_num"));
%>
<div>
<div id="nav"><jsp:include page="include/top.jsp" flush="true"></jsp:include></div>
<div id="content">
<div id="menu_up">
<div id="menu">
<div>
<ul>
<li><a href="user_album.jsp">我的相册</a></li>			
<li class="current_page_item"><a href="photo_upload.jsp">上传照片</a></li>
<li><a href="album_create.jsp">创建相册</a></li>	
<li><a href="album_home.jsp">相册中心</a></li>	
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
				out.println("<div align='center'><a class='success_style'>上传成功</a><a href='album_view.jsp?album_name="
							    +URLEncoder.encode(album_name, "UTF-8")
								+"&photo_num="+photo_num+"' class='return_style'>返回相册查看</a><a href='photo_upload.jsp?album_name="
						        +URLEncoder.encode(album_name, "UTF-8")
						        +"' class='continue_style'>继续上传</a></div>");
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

