<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>�ϴ��ɹ�</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/myblog.js"></script>
	</head>

<body>
<%
String album_name=URLDecoder.decode(request.getParameter("album_name"), "UTF-8");
%>
<div>
<div id="nav"><jsp:include page="../top.jsp" flush="true"></jsp:include></div>
<div id="content">
<div id="menu_up">
<div id="menu">
<div>
<ul>
<li><a href="user_album.jsp">�ҵ����</a></li>			
<li class="current_page_item"><a href="photo_upload.jsp">�ϴ���Ƭ</a></li>
<li><a href="album_create.jsp">�������</a></li>	
<li><a href="album_home.jsp">�������</a></li>	
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
				out.println("<div align='center' >");
				out.println("<div class='success_style'>�ϴ��ɹ�</div>");
				out.println("<div><a href='album_view.jsp?album_name="
						+URLEncoder.encode(album_name, "UTF-8")
						+"' class='return_style'>�������鿴</a><a href='photo_upload.jsp?album_name="
						+URLEncoder.encode(album_name, "UTF-8")
						+"' class='continue_style'>�����ϴ�</a></div>");
				out.println("</div>");
				%>
			</div>
		</div>
	</div>
</div>
</div>
<div style="clear:both"></div>
</div>
<jsp:include page="../bottom.html" flush="true"></jsp:include>
</div>
</body>
</html>

