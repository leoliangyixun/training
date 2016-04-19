<%@page import="com.yangkai.myblog.tools.PagingUtil"%>
<%@page import="com.yangkai.myblog.domain.Album"%>
<%@page import="com.yangkai.myblog.domain.Photo"%>
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
	<li class="current_page_item"><a href="album_list.jsp">相册列表</a></li>
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
					String loginuser=(String)session.getAttribute("loginuser");
					String curr_page=request.getParameter("page");
					String curr_step=request.getParameter("step");
					Map<String, List<Photo>> album_map=(Map<String, List<Photo>>)session.getAttribute("album_map");
					List<String> album_map_key=(List<String>)session.getAttribute("album_map_key");//由album_map的key组成的集合。
					List<Album> user_album=(List<Album>)session.getAttribute("user_album");
					if(album_map!=null && album_map.size()>0)
					{
					    Integer currpage=null;
					    Integer countpage=null;
					    int pagesize=3;
					    Integer countstep=null;
					    Integer currstep=null;
					    int pagestep=5;
					    countpage=PagingUtil.getCountpage(album_map.size(), pagesize);
					    countstep=PagingUtil.getCountstep(album_map.size(), pagesize, pagestep);
					    currpage=PagingUtil.getCurrentpage(curr_page, countpage);
					    currstep=PagingUtil.getCurrentstep(curr_step, countstep); 
						out.println("<div id='subject_list'>");
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						out.println("</div>");
					}
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

