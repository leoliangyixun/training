<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.yangkai.myblog.domain.MessageReply"%>
<%@page import="com.yangkai.myblog.tools.PagingUtil"%>
<%@page import="com.yangkai.myblog.domain.Message"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String curr_page=request.getParameter("page");
	String curr_step=request.getParameter("step");
	String loginuser=(String)session.getAttribute("loginuser");
	List<Message> user_message = (List<Message>) session.getAttribute("user_message");
	List<MessageReply> all_message_reply=(List<MessageReply>) session.getAttribute("all_message_reply");
	out.println("<div style='margin-left:7px; border:#E97B56 solid 1px;margin-top:20px;'>");
	if(user_message!=null && user_message.size()>0)
	{
			Integer currpage=null;
			Integer countpage=null;
			int pagesize=1;
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
					 out.println("<table border='0' width='570'  style='margin:10px auto;'>");
					 out.println("<tr>");
					 out.println("<td rowspan='2' width='110' align='center' valign='top' style='font-size:16px'>");
					 out.println("<img src='upload/相册/"+guest+"/image/me.jpg' width='110' height='110''/></br>");
					 out.println("<a style='font-size:12px;color:red'>"+guest+"</a></br></td>");

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
		    	out.println("<a href='javascript:getMyMessage("+(pagestep*(currstep-1))+","+(currstep-1)+")'>&laquo;</a>");
		    	for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
		    	{	
		    		out.println("<a href='javascript:getMyMessage("+k+","+currstep+")'>"+k+"</a>");
		    	}
		    	int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
		    	out.println("<a href='javascript:getMyMessage("+max+","+(currstep+1)+")'>&raquo;</a>");	
		    }else{
		    	if(countpage>1)
		    	{
		    		for(int k=1;k<=countpage;k++)
		    		{
		    			out.println("<a href='javascript:getMyMessage("+k+","+null+")'>"+k+"</a>");
		    		}
		    	}
		    }
		    out.println("</div>");	
		}else{
			out.println("<div align='center'><a class='success_style'>留言板为空!!!</a></div>");
		}
	out.println("</div>");
%>


