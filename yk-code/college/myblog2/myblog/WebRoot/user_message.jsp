<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.yangkai.myblog.domain.MessageReply"%>
<%@page import="com.yangkai.myblog.tools.PagingParamTool"%>
<%@page import="com.yangkai.myblog.domain.Message"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>����</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/myblog.js"></script>
	</head>

<body>
<%
String curr_page=request.getParameter("page");
String curr_step=request.getParameter("step");
String loginuser=(String)session.getAttribute("loginuser");
List<Message> user_message = (List<Message>) session.getAttribute("user_message");
//List<MessageReply> user_message_reply = (List<MessageReply>) session.getAttribute("user_message_reply");
List<MessageReply> all_message_reply=(List<MessageReply>) session.getAttribute("all_message_reply");
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
			<div id="navigation_style">
				<div id="link_list_style">
				    <a href="user_message.jsp" style="background-color:#E97B56;">���ҵ�����</a>
					<a href="my_leave_message.jsp">��д������</a>
					<a href="message_to_friends.jsp">����������</a>	
				</div>
			</div>
			<%
			if(user_message!=null && user_message.size()>0)
			{
		        Integer currpage=null;
		  	    Integer countpage=null;
		  	    int pagesize=3;
		  	    Integer countstep=null;
		  	    Integer currstep=null;
		  	    int pagestep=5;
		  	    countpage=PagingParamTool.getCountpage(user_message.size(), pagesize);
		  	    countstep=PagingParamTool.getCountstep(user_message.size(), pagesize, pagestep);
		  	    currpage=PagingParamTool.getCurrentpage(curr_page, countpage);
		  	    currstep=PagingParamTool.getCurrentstep(curr_step, countstep);   
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
						 out.println("<td rowspan='2' width='110' align='left' valign='top' style='font-size:12px'>");
						 out.println("<img src='upload/���/"+guest+"/image/me.jpg' width='110' height='110''/></br>");
						 out.println("<a style='font-size:12px;color:red'>"+guest+"</a></br>");
						 out.println("</td>");
						 /*
						 out.println("<td align='center' width='150'><a style='font-size:12px;margin-right:20px;'>"+guest+"</a></td>");			
						 out.println("<td align='center' width='100'><a href='message_add.jsp?username="
								     +URLEncoder.encode(guest, "UTF-8")+"' style='font-size:12px'>����</a></td>");
						*/
						 out.println("<td width='260' align='left'><a style='font-size:12px;'>"+new SimpleDateFormat("yyyy-MM-dd HH:mm").format(message_date)+"</a></td>");
						 out.println("<td align='right' width='200'><a href='javascript:showMessageReplyTextarea("+message_id+","
				 				 +currpage+","+currstep+","+Constants.ADD_MESSAGE_REPLY_FROM_USER_MESSAGE+")' >�ظ�����</a><a href='DeleteMessage?message_id="+message_id
				 	 			 +"&page="+currpage+"&step="+currstep+"&message_mark="+Constants.DELETE_MESSAGE_FROM_USER_MESSAGE+"' style='margin-left:10px'>ɾ������</a></td>");
						 out.println("</tr>");
						 out.println("<tr><td colspan='2' align='left'>"+message_content+"</td></tr>");
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
									out.println("<td rowspan='2' width='60' align='left' valign='top' style='font-size:12px'>");
									out.println("<img src='upload/���/"+messagereply.getUsername()+"/image/me.jpg' width='60' height='60''/><br>");
									out.println("<a>"+new SimpleDateFormat("yyyy-MM-dd HH:mm").format(messagereply.getMessagereplydate())+"</a><br>");
									out.println("</td>");
									
									out.println("<td align='right'>");
									//if(messagereply.getUsername().equals(loginuser))//�����á�==����Ҫ��equals��
									//{
										out.println("<a href='DeleteMessageReply?messsage_reply_id="
													+messagereply.getMessagereplyid()
													+"&page="+currpage
												    +"&step="+currstep+"&message_link_mark="+Constants.DELETE_MESSAGE_REPLY_FROM_USER_MESSAGE+"'>ɾ���ظ�</a>");
									//}
									out.println("</td>");
									
									out.println("</tr>");
									out.println("<tr>");
									out.println("<td valign='top' align='left'>"+messagereply.getMessagereplycontent()+"</td>");
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
									out.println("<img src='upload/���/"+messagereply.getUsername()+"/image/me.jpg' width='60' height='60''/><br>");
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
											    +"&step="+currstep+"'>ɾ���ظ�</a></div>");
					 			 }
					 		 }
					 	 }
						 */
						 /*
					 	 out.println("<div align='right' class='link_style'><a href='javascript:showMessageReplyTextarea("+message_id+","
					 				 +currpage+","+currstep+")'>�ظ�����</a><a href='DeleteMessage?message_id="+message_id
					 	 			 +"&page="+currpage+"&step="+currstep+"'>ɾ������</a></div>");
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
				out.println("<div align='center'><a class='success_style'>��û���˸�������!!!</a></div>");
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

