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
			<h1><a>һ���˵İ���</a></h1>
            <p><a>��ˮ���ֻ꣬Ϊ���ˡ�</a></p>
		</div>
		<div id="top_search">
	      <div id="welcome"><div>��ӭ��:<a href="#"><%=loginuser%></a><a href="LoginOut">��ȫ�˳�</a></div></div>
	    </div>
    </div>
	<div id="menu_style">
		<div id="top_menu">
			<ul>
				<li><a href="index.jsp">��ҳ</a></li>
				<li><a href="user_blog.jsp">����</a></li>
				<li><a href="user_album.jsp">���</a></li>
				<li><a href="user_mood.jsp">˵˵</a></li>
			</ul>
		</div>
	</div>
</div>
</div>
</div>
</body>
</html>

