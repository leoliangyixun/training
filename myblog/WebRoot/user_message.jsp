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
	<title>留言</title>
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
	    <li><a href="friends_blog.jsp">好友动态</a></li>
		<li><a href="user_blog.jsp">我的博客</a></li>			
		<li><a href="user_album.jsp">我的相册</a></li>
		<li><a href="user_mood.jsp">我的说说</a></li>
		<li class="current_page_item"><a href="user_message.jsp">留言板</a></li>
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
				<div id="link_list_style">
				    <a href="user_message.jsp" style="background-color:#E97B56;">给我的留言</a>
					<a href="my_leave_message.jsp">我写的留言</a>
					<a href="message_to_friends.jsp">给好友留言</a>	
					<a href="message_list.jsp">留言管理</a>
				</div>
			<%
				String curr_page=request.getParameter("page");
				String curr_step=request.getParameter("step");
				String loginuser=(String)session.getAttribute("loginuser");
				List<Message> user_message = (List<Message>) session.getAttribute("user_message");
				List<MessageReply> all_message_reply=(List<MessageReply>) session.getAttribute("all_message_reply");
				if(user_message!=null && user_message.size()>0)
				{
					Integer currpage=null;
					Integer countpage=null;
					int pagesize=3;
					Integer countstep=null;
					Integer currstep=null;
					int pagestep=5;
					countpage=PagingUtil.getCountpage(user_message.size(), pagesize);
					countstep=PagingUtil.getCountstep(user_message.size(), pagesize, pagestep);
					currpage=PagingUtil.getCurrentpage(curr_page, countpage);
					currstep=PagingUtil.getCurrentstep(curr_step, countstep);   
					for (int i = (currpage-1)*pagesize;i < currpage*pagesize; i++) 
					{
						if(i<user_message.size())
						{
							 Message msg= user_message.get(i);
							 int message_id = msg.getMessageid();
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
							 /*
							 out.println("<td align='center' width='150'><a style='font-size:12px;margin-right:20px;'>"+guest+"</a></td>");			
							 out.println("<td align='center' width='100'><a href='message_add.jsp?username="
										 +URLEncoder.encode(guest, "UTF-8")+"' style='font-size:12px'>留言</a></td>");
							*/
							 out.println("<td colspan='2' align='left' valign='top'>"+message_content+"</td>");
							 out.println("</tr>");
							
							 out.println("<td width='260' align='left' height='20'>"+new SimpleDateFormat("yyyy-MM-dd HH:mm").format(message_date)+"</td>");
							 out.println("<td align='right' width='200' height='20'><a href='javascript:showMessageReplyTextarea("+message_id+","
									 +currpage+","+currstep+","+Constants.ADD_MESSAGE_REPLY_FROM_USER_MESSAGE+")' >回复留言</a><a href='DeleteMessage?message_id="+message_id
									 +"&page="+currpage+"&step="+currstep+"&message_mark="+Constants.DELETE_MESSAGE_FROM_USER_MESSAGE+"' style='margin-left:10px'>删除留言</a></td>");

							 out.println("</table>");
							 out.println("</div>");
							 
							 if(all_message_reply!=null && all_message_reply.size()>0)
							 {
								 for(int j=0;j<all_message_reply.size();j++)
								 {
									 if(all_message_reply.get(j).getMessageid()==message_id)
									 {
										MessageReply messagereply=all_message_reply.get(j);
										out.println("<table border='0' width='458' bgcolor='#FFFF80' bordercolor='#FFFFFF' style='margin-left:117px;margin-bottom:10px;border-collapse: collapse;'>");
										
										out.println("<tr>");
										out.println("<td rowspan='2' width='60' align='center' valign='top' style='font-size:12px'>");
										out.println("<img src='upload/相册/"+messagereply.getUsername()+"/image/me.jpg' width='60' height='60''/><br>");
										out.println("<a>"+new SimpleDateFormat("yyyy-MM-dd HH:mm").format(messagereply.getMessagereplydate())+"</a><br>");
										out.println("</td>");
										out.println("<td valign='top' align='left'>"+messagereply.getMessagereplycontent()+"</td>");
										out.println("</td>");
										out.println("</tr>");
										out.println("<tr>");
										out.println("<td align='right' height='20'>");
										out.println("<a href='DeleteMessageReply?messsage_reply_id="
															+messagereply.getMessagereplyid()
															+"&page="+currpage
															+"&step="+currstep+"&message_link_mark="+Constants.DELETE_MESSAGE_REPLY_FROM_USER_MESSAGE+"'>删除回复</a>");
										out.println("</tr>");
										out.println("</table>");
									 }
								 }
							 }
							 
							 /*
							 if(user_message_reply!=null && user_message_reply.size()>0)
							 {
								 for(int j=0;j<user_message_reply.size();j++)
								 {
									 if(user_message_reply.get(j).getMessageid()==message_id)
									 {
										MessageReply messagereply=user_message_reply.get(j);
										
										out.println("<table border='1' width='498' bgcolor='#FFFF80' bordercolor='#FFFFFF' style='margin-left:77px;border-collapse: collapse;'>");
										out.println("<tr>");
										out.println("<td width='70' align='center' valign='middle' style='font-size:12px'>");
										out.println("<img src='upload/相册/"+messagereply.getUsername()+"/image/me.jpg' width='60' height='60''/><br>");
										out.println("<a>"+messagereply.getMessagereplydate()+"</a><br>");
										out.println("</td>");
										out.println("<td valign='center' align='left'>");
										out.println(messagereply.getMessagereplycontent());
										out.println("</td>");
										out.println("</tr>");
										out.println("</table>");
										out.println("<div style='margin-left:80px;'><a href='DeleteMessageReply?messsage_reply_id="
													+messagereply.getMessagereplyid()
													+"&page="+currpage
													+"&step="+currstep+"'>删除回复</a></div>");
									 }
								 }
							 }
							 */
							 /*
							 out.println("<div align='right' class='link_style'><a href='javascript:showMessageReplyTextarea("+message_id+","
										 +currpage+","+currstep+")'>回复留言</a><a href='DeleteMessage?message_id="+message_id
										 +"&page="+currpage+"&step="+currstep+"'>删除留言</a></div>");
							 */
							 out.println("<div id='message_reply_textarea"+message_id+"'></div>");
							 out.println("</div>");//end of border
							 out.println("</div>");//end of single_list
						}
						else{
							break;
						}
					}//end of for
			
					out.println("<div align='center' id='pages'>");
					if(countpage>pagestep)
					{
						out.println("<a href='user_message.jsp?page="+(pagestep*(currstep-1))+"&step="+(currstep-1)+"'>&laquo;</a>");
						for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
						{
							out.println("<a href='user_message.jsp?page="+k+"&step="+currstep+"'>"+k+"</a>");
						}
						int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
						out.println("<a href='user_message.jsp?page="+max+"&step="+(currstep+1)+"'>&raquo;</a>");	
					}else{
						if(countpage>1)
						{
							for(int k=1;k<=countpage;k++)
							{
								out.println("<a href='user_message.jsp?page="+k+"'>"+k+"</a>");
							}
						}
					}
					out.println("</div>");	
				}else{
						out.println("<div align='center'><a class='success_style'>留言板为空!!!</a></div>");
				}
			%>
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

