<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>
    <title>����ҳ</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="../js/myblog.js"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
  </head>
  <body>
	<div>
	<div id="nav"><jsp:include page="top.jsp" flush="true"></jsp:include></div>
	<div id="content">
	<div id="menu_up">
	<div id="menu">
	<div>
	<ul>
	<li><a href="../index.jsp">������ҳ</a></li>
	<li><a href="../user_blog.jsp">���ز���</a></li>
	<li><a href="../user_album.jsp">�������</a></li>
	<li><a href="../user_mood.jsp">����˵˵</a></li>
	<li><a href="../user_message.jsp">��������</a></li>				
	</ul>
	</div>
	</div>
	</div>
	<div id="list">
	<div id="list_top">
		<div id="list_middle"> 
			<div id="list_bottom">
				<div id="blog_list">
					<div align="center"><a class="error_style">��������<a/></div>
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
