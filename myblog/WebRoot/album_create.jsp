<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>创建相册</title>
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
			<li><a href="photo_upload.jsp">上传照片</a></li>
			<li  class="current_page_item"><a href="album_create.jsp">创建相册</a></li>	
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
				    <a href="user_album.jsp" >我的相册</a>
					<a href="photo_upload.jsp">上传照片</a>
					<a href="album_create.jsp" style="background-color:#E97B56;">创建相册</a>	
				</div>
					<div align="center">
					  <fieldset id="field">
					    <legend id="area">创建相册</legend>
					    <div align="center" id="pic_upload_area">
						<form action="CreateAlbum" method="post" name="albumForm">
						<table>
		    				<tr align="left">
		      					<td>相册名称：</td>
		     					<td><input type="text" name="album_name"  size="20" onblur="sendCheckAlbumNameRequest()"/><label id="exsist_album_name"></label></td>
		   				    </tr>
		   				    <tr align="left">
		      					<td>相册描述：</td>
		     					<td><textarea name="album_desc" rows="5" cols="40"></textarea></td>
		   				    </tr>
		     				<tr>
		     				    <td><input type='hidden' name='create_date' value='<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())%>'></td>
		     				    <td align="center">
		     				    <input type="submit" value="提交" />
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

