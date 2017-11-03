<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.yangkai.myblog.domain.MessageReply"%>
<%@page import="com.yangkai.myblog.tools.com.yangkai.myblog.tools.PageApart"%>
<%@page import="com.yangkai.myblog.domain.Message"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>����</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/myblog.js"></script>
	</head>

<body>
<%
	String loginuser=(String)session.getAttribute("loginuser");
%>
<div>
<div id="nav"><jsp:include page="include/top.jsp" flush="true"></jsp:include></div>
<div id="content">
<div id="menu_up">
<div id="menu">
<div>
	<ul>
		<li class="current_page_item"><a href="user_message.jsp">�ҵ�����</a></li>
		<li><a href="message_list.jsp">���Թ���</a></li>
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
				    <a href="user_message.jsp">���ҵ�����</a>
					<a href="my_leave_message.jsp" style="background-color:#E97B56;">��д������</a>
					<a href="message_to_friends.jsp">���������</a>
					
				</div>
			<%
				String curr_page=request.getParameter("page");
				String curr_step=request.getParameter("step");
				List<Message> my_leave_message = (List<Message>) session.getAttribute("my_leave_message");
				//List<MessageReply> my_leave_message_reply = (List<MessageReply>) session.getAttribute("my_leave_message_reply");
				List<MessageReply> all_message_reply=(List<MessageReply>) session.getAttribute("all_message_reply");
				
				if(my_leave_message!=null && my_leave_message.size()>0)
				{
					Integer currpage=null;
					Integer countpage=null;
					int pagesize=3;
					Integer countstep=null;
					Integer currstep=null;
					int pagestep=5;
					countpage=PageApart.getCountpage(my_leave_message.size(), pagesize);
					countstep=PageApart.getCountstep(my_leave_message.size(), pagesize, pagestep);
					currpage=PageApart.getCurrentpage(curr_page, countpage);
					currstep=PageApart.getCurrentstep(curr_step, countstep);      
					for (int i = (currpage-1)*pagesize;i < currpage*pagesize; i++) 
					{
						if(i<my_leave_message.size())
						{
							 Message msg= my_leave_message.get(i);
							 int message_id = msg.getMessageid();
							 String username=msg.getUsername();
							 String guest=msg.getGuest();
							 String message_content = msg.getMessagecontent();
							 Date message_date = msg.getMessagedate();
							 out.println("<div id='single_list'>");
							 out.println("<div class=''>");
							 out.println("<div>");
							 out.println("<table border='1' width='570' bgcolor='#FFFF80' bordercolor='#FFFFFF' style='margin:10px auto;border-collapse: collapse;'>");
							 //out.println("<tr><td colspan='3'><span style='font-size:12px;margin-right:20px;'>��<a style='color:red;font-size:16px'>"+username+"</a>�����ԣ�</span></td></tr>");
							 out.println("<tr>");
							 out.println("<td rowspan='2' width='110' align='left' valign='top' style='font-size:12px'>");
							 out.println("<img src='upload/���/"+username+"/image/me.jpg' width='110' height='110'/></br>");
							 out.println("<a style='font-size:16px'>To:</a><a style='font-size:12px;color:red'>"+username+"</a>");
							 //out.println("<a style='color:red'>"+loginuser+"</a>");
							 out.println("</td>");
							// out.println("<td align='left' width='150'><a style='font-size:12px;margin-right:20px;'>��"+username+"������</a></td>");
											

							 out.println("<td width='230' align='left' valign='top'>"+new SimpleDateFormat("yyyy-MM-dd HH:mm").format(message_date)+"</td>");
							/*
							 out.println("<td align='right' width='310'><a href='message_add.jsp?username="
										+URLEncoder.encode(username, "UTF-8")+"' style='font-size:12px'>�������</a></td>");
							*/
							 out.println("<td align='right' width='230'><a href='javascript:showMessageReplyTextarea("+message_id+","
					 				     +currpage+","+currstep+","+Constants.ADD_MESSAGE_REPLY_FROM_MY_LEAVE_MESSAGE+")' >�ظ�����</a></td>");
							 out.println("</tr>");
							 out.println("<tr>");
							 out.println("<td colspan='2' align='left'>"+message_content+"</td>");
							 out.println("</tr>");
							 out.println("</table>");
							 out.println("</div>");
							 
							 if(all_message_reply!=null && all_message_reply.size()>0)
							 {
								 for(int j=0;j<all_message_reply.size();j++)
								 {
									 if(all_message_reply.get(j).getMessageid()==message_id)
									 {
										 MessageReply messagereply=all_message_reply.get(j);
										 out.println("<table border='1' width='458' bgcolor='#FFFF80' bordercolor='#FFFFFF' style='margin-left:117px;margin-bottom:10px;border-collapse: collapse;'>");
										 out.println("<tr>");
										 out.println("<td rowspan='2' width='60' align='left' valign='top' style='font-size:12px'>");
										 
										 out.println("<img src='upload/���/"+messagereply.getUsername()+"/image/me.jpg' width='60' height='60''/><br>");
										 out.println("<a style='font-size:12px;'>"+new SimpleDateFormat("yyyy-MM-dd HH:mm").format(messagereply.getMessagereplydate())+"</a>");
										 out.println("</td>");
										 out.println("<td align='left' width='200'><a style='font-size:12px;color:red'>"+messagereply.getUsername()+"</a>�ظ���</td>");
										 out.println("<td align='right' width='198'>");	
										
										 out.println("<a href='DeleteMessageReply?messsage_reply_id="+messagereply.getMessagereplyid()+"&page="+currpage
												     +"&step="+currstep+"&message_link_mark="+Constants.DELETE_MESSAGE_REPLY_FROM_MY_LEAVE_MESSAGE+"' >ɾ��ظ�</a>");
										
										 out.println("</td>");
										 out.println("</tr>");
										 out.println("<tr>");
										 out.println("<td colspan='2' valign='top' align='left'>"+messagereply.getMessagereplycontent()+"</td>");
										 
										 out.println("</tr>");
										 out.println("</table>");	

									 }
								 }
							 }
							 /*
							 if(my_leave_message_reply!=null && my_leave_message_reply.size()>0)
							 {
								 for(int j=0;j<my_leave_message_reply.size();j++)
								 {
									 if(my_leave_message_reply.get(j).getMessageid()==message_id)
									 {
										 MessageReply messagereply=my_leave_message_reply.get(j);
										 out.println("<table border='1' width='508' bgcolor='#FFFF80' bordercolor='#FFFFFF' style='margin-left:67px;margin-bottom:10px;border-collapse: collapse;'>");
										 out.println("<tr>");
										 out.println("<td width='60' align='center' valign='middle' style='font-size:12px'>");
										 
										 out.println("<img src='upload/���/"+messagereply.getUsername()+"/image/me.jpg' width='60' height='60''/><br>");
										 out.println("<a>"+messagereply.getMessagereplydate()+"</a><br>");
										 out.println("</td>");
										 out.println("<td valign='center' align='left'>");
										 out.println(messagereply.getMessagereplycontent());
										 out.println("</td>");
										 out.println("</tr>");
										 out.println("</table>");	
									 }
								 }
							 }
							 */
							/*
							 out.println("<div align='right' class='link_style'><a href='javascript:showMessageReplyTextarea("
										+message_id+","+currpage+","+currstep+")'>�ظ�����</a><a href='DeleteMessage?message_id="
										+message_id+"&page="+currpage+"&step="+currstep+"'>ɾ������</a></div>");
							 out.println("<div id='message_reply_textarea"+message_id+"'></div>");
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
					if(countpage>=pagestep)
					{
						out.println("<a href='my_leave_message.jsp?page="+(pagestep*(currstep-1))+"&step="+(currstep-1)+"'>&laquo;</a>");
						for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
						{
							out.println("<a href='my_leave_message.jsp?page="+k+"&step="+currstep+"'>"+k+"</a>");
						}
						int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
						out.println("<a href='my_leave_message.jsp?page="+max+"&step="+(currstep+1)+"'>&raquo;</a>");	
					}else{
						if(countpage>1)
						{
							for(int k=1;k<=countpage;k++)
							{
								out.println("<a href='my_leave_message.jsp?page="+k+"'>"+k+"</a>");
							}
						}
					}
					out.println("</div>");
				}else{
					out.println("<div align='center'><a class='success_style'>�㻹û��д������!!!</a></div>");
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