<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.yangkai.myblog.domain.User"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>添加成功</title>
	<link href="../css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="../js/myblog.js"></script>
	<style type="text/css">
#submit{ 
	width: 51px;
	height: 20px;
	border: none;
	/*background: none;*/
	text-indent: -99999px; 
	background-image:url(css/images/submit.gif);
} 
#reset{ 
	width: 51px;
	height: 20px;
	border: none;
	text-indent: -99999px; 
	background-image:url(css/images/reset.gif);
}
#register{ 
	width: 51px;
	height: 20px;
	border: none;
	text-indent: -99999px; 
	background-image:url(css/images/register.gif);
}
#login input{
	margin-right: 5px;
}
#welcome {
	margin-top: 20px;
} 

#welcome a{
	margin-right: 5px;
	font-size: 12px;
	color: black;
}
.input{
	font-family: "宋体";
	font-size: 9pt;
	background-color:#DFD4A7;
	border: 1px solid #D1B681;
}  
</style>
	</head>

<body>
<% 
	User bloger=(User)session.getAttribute("bloger");
%>
<div>
<div id="nav">
	<div id="top_wrapper">
	<div id="top_header">
		<div id="top_logo">
		<%
		if(bloger==null) 
		{%>
		<h1><a>枫雅博客</a></h1>
        <p><a>枫雅博客，陪伴你左右，记录生活的精彩</a></p>
		<%}else{
			String blog_name=null;
			String blog_logo=null;
			if(bloger.getBlogname()==null){
				blog_name="枫雅博客";
			}else{
				blog_name=bloger.getBlogname();
			}
			if(bloger.getBloglogo()==null){
				blog_logo="枫雅博客，陪伴你左右，记录生活的精彩";
			}else{
				blog_logo=bloger.getBloglogo();
			}
			out.println("<h1><a>"+blog_name+"</a></h1>");
			out.println("<p><a>"+blog_logo+"</a></p>");
		}
		%>
		</div>
		<div id="top_search">
	      <div id="welcome"><span>
	      <table border="0">
	      <tr>
		      <td><img src="../upload/相册/<%=bloger.getUsername()%>/image/me.jpg" width="30" height="30""/></td>
		      <td valign="top"><a href="user_setting.jsp" style="margin-left:5px"><%=bloger.getUsername()%></a><a href="LoginOut">[退出]</a></td>
	      </tr>
	      </table><span></div>
		
	</div>
   
</div>
	<!-- end #header -->
	<div id="top_menu">
		<ul>
			<li><a href="../index.jsp">首页</a></li>
		    <li><a href="../user_blog.jsp">博客</a></li>
		    <li><a href="../user_album.jsp">相册</a></li>
		    <li><a href="../user_mood.jsp">说说</a></li>
			<li><a href="../user_message.jsp">留言</a></li>
			<li><a href="../user_home.jsp">个人中心</a></li>
		</ul>
	</div>
</div>

</div>
<div id="content">
<div id="menu_up">
<div id="menu">
<div>
	<ul>
		<li><a href="../blog_list.jsp">博文列表</a></li>
		<li class="current_page_item"><a href="../blogclass_add.jsp">新建分类</a></li>			
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

