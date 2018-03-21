<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.yangkai.myblog.tools.PagingUtil"%>
<%@page import="com.yangkai.myblog.domain.MoodCommentReply"%>
<%@page import="com.yangkai.myblog.domain.MoodComment"%>
<%@page import="com.yangkai.myblog.domain.Mood"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>我的说说</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/myblog.js"></script>
	<script type="text/javascript"  language="javascript">
	<!--
		getMyMood(null,null);	
	-->
	</script>
	</head>

<body>

<div>
<div id="nav"><jsp:include page="include/top.jsp" flush="true"></jsp:include></div>
<div id="content">
<div id="menu_up">
<div id="menu">
<div>
<ul>
 	<li><a href="friends_blog.jsp">好友动态</a></li>
	<li><a href="user_blog.jsp">我的博客</a></li>			
	<li><a href="user_album.jsp">我的相册</a></li>
	<li class="current_page_item"><a href="user_mood.jsp">我的说说</a></li>
	<li><a href="user_message.jsp">留言板</a></li>
	<li><a href="user_home.jsp">个人中心</a></li>
</ul>
</div>
</div>
</div>
<div id="list">
<div id="list_top">
	<div id="list_middle">
		<div id="list_bottom">
			<div id="blog_list">
				<div id="mood_style">
					<div id="mood_title_style">记录今天的点点滴滴......</div>
					<div>
						<form action="AddMood" method="post">
							<table width="580" border="0">
							  <tr>
							    <td><textarea name="mood_content" id="textarea_border" cols="80" rows="4" ></textarea></td>
							  </tr>
							  <tr>
							    <td>
							      <input type="hidden" name="mood_date" value="<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())%>"/>
							      <input type="submit"  value="发表" style="font-size: 18px;padding:0 14px;margin-right:10px;font-weight: bold;font-family:微软雅黑;"/>
							      <input type="reset"  value="取消" style="font-size: 18px;padding:0 14px;font-weight: bold;font-family:微软雅黑;"/>
							    </td>
							  </tr>
							</table>
						</form>
					</div>
				</div>		
				<div id="mood_style">		
					<div id="navigator_list_style">
						<a href="javascript:getMyMood(null,null)">我的说说</a>
						<a href="javascript:getFriendsMood(null,null)">好友说说</a>
					</div>
				</div>
				<div id="my_dynamic" ></div>
			</div>
		</div>
	</div>
</div>

</div>
<div class="clear"></div>
</div>
<jsp:include page="include/bottom.html" flush="true"></jsp:include>
</div>
</body>
</html>

