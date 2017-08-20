<%@page import="java.net.URLEncoder"%>
<%@page import="com.yangkai.myblog.domain.MessageReply"%>
<%@page import="com.yangkai.myblog.tools.PagingParamTool"%>
<%@page import="com.yangkai.myblog.domain.Message"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>留言</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/myblog.js"></script>
	</head>

<body>

<div>
<%
/*
String currpage=request.getParameter("page");
String currstep=request.getParameter("step");
if(request.getParameter("ajax_message_mark")==null)
{
	int ajax_message_mark=Integer.parseInt(request.getParameter("ajax_message_mark"));
	out.println("<script>sendAjaxMessageRequest("+Constants.MESSAGE_FOR_ME+")</script>");
	out.println("<script>sendAjaxMessageMark("+ajax_message_mark+","+currpage+","+currstep+");</script>");
}
else{
	out.println("<script>sendAjaxMessageMark("+Constants.MESSAGE_FOR_USER+","+currpage+","+currstep+");</script>");
}
*/
//out.println("<script>findMyMessage("+null+","+null+")</script>");
out.println("<script>findMyMessage(null,null)</script>");
%>
<div id="nav"><jsp:include page="top.jsp" flush="true"></jsp:include></div>
<div id="content">
<div id="menu_up">
<div id="menu">
<div>
	<ul>
		<li><a href="user_message.jsp">我的留言</a></li>
		<li class="current_page_item"><a href="message_manage.jsp">留言管理</a></li>
	</ul>
</div>
</div>
</div>
<div id="list">
<div id="list_top">
	<div id="list_middle">
		<div id="list_bottom">
			<div id="blog_list">
			    <!--div id="message_style">
				    <a href="message_manage.jsp?ajax_message_mark=<%=Constants.MESSAGE_FOR_USER%>">查看给我的留言</a>
				    <a href="message_manage.jsp?ajax_message_mark=<%=Constants.MESSAGE_FOR_OTHER%>">查看我写的留言</a>
			    </div-->
				<div id="ajax_message_style">
					<a href="javascript:findMyMessage(null,null)">给我的留言</a>
					<a href="javascript:findMyLeaveMessage(null,null)">我写的留言</a>
					<a href="javascript:manageMessageBox(null,null)">给好友留言</a>
					<a href="javascript:manageMessageBox(null,null)">留言板管理</a>
				</div>
				<div id="message"></div>
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