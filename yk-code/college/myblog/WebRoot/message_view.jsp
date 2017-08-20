<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.yangkai.myblog.domain.MessageReply"%>
<%@page import="com.yangkai.myblog.tools.PagingUtil"%>
<%@page import="com.yangkai.myblog.domain.Message"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>My JSP 'index.jsp' starting page</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/myblog.js"></script>
	<style type="text/css">
	#regist {
		width:620px;
		margin:20px auto;
	}
</style>
	</head>

<body>
<div>
<div id="nav"><jsp:include page="include/top.jsp" flush="true"></jsp:include></div>
<div id="regist">

<div id="list">
<div id="list_top">
	<div id="list_middle">
		<div id="list_bottom">
			<div id="blog_list">
			<%
				String loginuser=(String)session.getAttribute("loginuser");
				int message_id=Integer.parseInt(request.getParameter("message_id"));
				List<Message> user_message = (List<Message>) session.getAttribute("user_message");
				List<MessageReply> all_message_reply=(List<MessageReply>) session.getAttribute("all_message_reply");
				for(int i=0;i<user_message.size();i++)
				{
					if(user_message.get(i).getMessageid()==message_id)
					{
						 Message msg= user_message.get(i);
						 String guest=msg.getGuest();
						 String message_content = msg.getMessagecontent();
						 Date message_date = msg.getMessagedate();
						 out.println("<div id='single_list'>");
						 out.println("<div class=''>");
						 out.println("<div>");
						 out.println("<table border='0' width='570' bgcolor='#FFFF80' bordercolor='#FFFFFF' style='margin:10px auto;border-collapse: collapse;'>");
						 out.println("<tr>");
						 out.println("<td rowspan='2' width='110' align='center' valign='top' style='font-size:16px'>");
						 out.println("<img src='upload/相册/"+guest+"/image/me.jpg' width='110' height='110''/></br>");
						 out.println("<a style='font-size:12px;color:red'>"+guest+"</a></br></td>");
						 out.println("<td colspan='2' align='left' valign='top'>"+message_content+"</td>");
						 out.println("</tr>");
						
						 out.println("<td width='260' align='left' height='20'>"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(message_date)+"</td>");
						 out.println("<td align='right' width='200' height='20'><a href='javascript:showMessageReplyTextarea("+message_id+","
								 +null+","+null+","+Constants.ADD_MESSAGE_REPLY_FROM_USER_MESSAGE_VIEW+")' >回复留言</a><a href='DeleteMessage?message_id="+message_id
								 +"&message_mark="+Constants.DELETE_MESSAGE_FROM_USER_MESSAGE_VIEW+"' style='margin-left:10px'>删除留言</a></td>");

						 out.println("</table>");
						 out.println("</div>");
						
						 if(all_message_reply!=null && all_message_reply.size()>0)
						 {
							 for(int j =0; j < all_message_reply.size(); j++)
							 {
								 if(all_message_reply.get(j).getMessageid()==message_id)
								 {
									MessageReply messagereply=all_message_reply.get(j);
									out.println("<table border='0' width='458' bgcolor='#FFFF80' bordercolor='#FFFFFF' style='margin-left:117px;margin-bottom:10px;border-collapse: collapse;'>");
									
									out.println("<tr>");
									out.println("<td rowspan='2' width='60' align='center' valign='top' style='font-size:12px'>");
									out.println("<img src='upload/相册/"+messagereply.getUsername()+"/image/me.jpg' width='60' height='60''/><br>");
									out.println("<a>"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(messagereply.getMessagereplydate())+"</a><br>");
									out.println("</td>");
									out.println("<td valign='top' align='left'>"+messagereply.getMessagereplycontent()+"</td>");
									out.println("</td>");
									out.println("</tr>");
									out.println("<tr>");
									out.println("<td align='right' height='20'>");
									out.println("<a href='DeleteMessageReply?messsage_reply_id="
														+messagereply.getMessagereplyid()
														+"&message_id="+message_id+"&message_link_mark="+Constants.DELETE_MESSAGE_REPLY_FROM_USER_MESSAGE_VIEW+"'>删除回复</a>");
									out.println("</tr>");
									out.println("</table>");
								 }
							 } 
						 }
							
						 out.println("<div id='message_reply_textarea"+message_id+"'></div>");
						 out.println("</div>");
						 out.println("</div>");
						break;
					}
				}
			%>
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
				<!--div>
					<table width="580" border="1">
					  <tr>
					    <td width="120"   rowspan="4">头像</td>
					    <td colspan="2" height="30">留言时间：</td>
					  </tr>
					  <tr>
					    <td height="60" colspan="2">内容1</td>
					  </tr>
					  <tr>
					    <td width="80" height="80" rowspan="2">头像</td>
					    <td>内容2</td>
					  </tr>
					  <tr>
					    <td>提交</td>
					  </tr>
					</table>
				</div-->
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

