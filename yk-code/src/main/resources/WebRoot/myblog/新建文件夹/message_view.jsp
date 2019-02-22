<%@page import="com.yangkai.myblog.domain.MessageReply"%>
<%@page import="com.yangkai.myblog.domain.Message"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>My JSP 'index.jsp' starting page</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/myblog.js"></script>
	</head>

<body>
<%
int message_id=Integer.parseInt(request.getParameter("message_id"));
List<Message> user_message = (List<Message>) session.getAttribute("user_message");
List<MessageReply> message_reply = (List<MessageReply>) session.getAttribute("message_reply");
%>
<div>
<div id="nav"><jsp:include page="include/top.jsp" flush="true"></jsp:include></div>
<div id="content">
<div id="menu_up">
<div id="menu">
<div>
	<ul>
		<li><a href="user_message.jsp">我的留言</a></li>
		<li class="current_page_item"><a href="my_leave_message.jsp">留言管理</a></li>
	</ul>
</div>
</div>
</div>
<div id="list">
<div id="list_top">
	<div id="list_middle">
		<div id="list_bottom">
			<div id="blog_list">
			<%--
			String guest;
			String username;
			String message_date;
			
			for(int i=0;i<user_message.size();i++)
			{
				if(user_message.get(i).getMessageid()==message_id)
				{
					guest=user_message.get(i).getGuest();
					username=user_message.get(i).getUsername();
				}
			}
			--%>
			<div>
			<table width="580" border="1">
			  <tr>
			    <td width="150" rowspan="4">头像</td>
			    <td colspan="2" height="30">留言时间：</td>
			  </tr>
			  <tr>
			    <td height="40" colspan="2">内容1</td>
			  </tr>
			  <tr>
			    <td width="100" rowspan="2">头像</td>
			    <td>内容2</td>
			  </tr>
			  <tr>
			    <td>提交</td>
			  </tr>
			</table>
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

