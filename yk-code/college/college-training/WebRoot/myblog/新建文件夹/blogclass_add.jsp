<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>新建分类</title>
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
		<li><a href="blog_list.jsp">博文列表</a></li>
		<li class="current_page_item"><a href="blogclass_add.jsp">新建分类</a></li>				
	</ul>
</div>
</div>
</div>
<div id="list">
<div id="list_top">
	<div id="list_middle">
		<div id="list_bottom">
			<div id="blog_list">
			<div align="center">
			 <fieldset id="field">
			  <legend id="area">新建文章分类</legend>
				 <div align="center" id="pic_upload_area">
				 <form method="post" name="blogclassForm">
					<table width="420">
		    			<tr>
		      			  <td width="90">类别名称：</td>
		     			  <td><input type="text" name="blog_class"  size="20" /><label id="exsist_blog_class"></label></td>
		   				</tr>
		     		    <tr>
		     			  <td><input type="hidden" name="blog_class_mark" value="1"/></td>
		     			  <td align="left">
		     			  <input type="button" value="创建" onclick="sendCheckBlogClassRequest()"/>
		     			  <input type="reset" value="重置" /></td>
		     			 </td>	
		    			</tr>
		  			</table>
				</form>
			</div>
			</fieldset>
			</div>
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

