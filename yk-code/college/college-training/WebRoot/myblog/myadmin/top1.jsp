<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>
    <title>My JSP 'top.jsp' starting page</title>
	<style type="text/css">
	body {
	margin: 0;
	padding: 0;
	background: #EFDABB url(../css/images/img01.jpg) repeat left top;
	font-size: 18px;
	color: #3E3B36;
	}
	#top_menu { /*width: 1000px;*/
	height: 54px;
	margin-left: 100px;; /*border:#000 dotted 2px; */
}
#nav{ /* border:#000 dotted 2px; */width:800px; margin:10px auto;}
#nav .logo{ font-size:24px;}
#nav .login{ margin-right:10px;}
#top_menu ul {
	padding: 0px 0px 0px 25px;
	list-style: none;
	line-height: normal;
	margin: 0;
}

#top_menu li {
	float: left;
	height: 38px;
}

#top_menu a {
	display: block;
	margin-right: 10px;
	margin-top: 5px;
	padding: 17px 30px 7px 30px;
	background: url(../css/images/img02_1.jpg) no-repeat right 18px;
	text-decoration: none;
	font-size: 22px;
	color: #000;
	border: none;
	padding: 15px 40px 15px 40px;
}


	</style>
  </head>
  
  <body>
	<%String loginuser=(String)session.getAttribute("loginuser"); %>
	<div id="nav">
    <div align="center">
		<div class="logo">枫雅博客后台管理</div>
		<div id="top_menu">
			<ul>
				<li><a href="index.jsp">首页</a></li>
				<li><a href="user_blog.jsp">博客</a></li>
				<li><a href="user_album.jsp">相册</a></li>
	            <li><a href="index.jsp">首页</a></li>
				<li><a href="user_blog.jsp">博客</a></li>
			</ul>
		</div>
        
        </div>
        <div align="right"><a>当前在线：</a></div>
	</div>
	</body>
	</html>
