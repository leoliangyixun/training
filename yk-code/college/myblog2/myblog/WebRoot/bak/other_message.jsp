<%@page import="java.net.URLEncoder"%>
<%@page import="com.yangkai.myblog.domain.MessageReply"%>
<%@page import="com.yangkai.myblog.tools.PagingParamTool"%>
<%@page import="com.yangkai.myblog.domain.Message"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String curr_page=request.getParameter("page");
String curr_step=request.getParameter("step");
List<Message> user_message = (List<Message>) session.getAttribute("user_message");
List<MessageReply> message_reply = (List<MessageReply>) session.getAttribute("message_reply");
if(user_message!=null && user_message.size()>0)
{
    Integer currpage=null;
    Integer countpage=null;
    int pagesize=2;
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
	         int message_id = msg.getMessageId();
	         String guest=msg.getGuest();
	         String message_content = msg.getMessagecontent();
	         String message_date = msg.getMessagedate();
	         out.println("<div id='single_list'>");
	         out.println("<div class='border'>");
	         out.println("<div>");
			 out.println("<table border='1' width='570' bgcolor='#FFFF80' bordercolor='#000000' style='margin:10px auto;border-collapse: collapse;'>");
			 out.println("<tr>");
			 out.println("<td rowspan='2' width='70' align='center' valign='top' style='font-size:12px'>");
			 out.println("<img src='upload/error.jpg' width='60' height='60''/>");
			 out.println("</td>");
			 out.println("<td align='left' width='150'><a style='font-size:12px;margin-right:20px;'>"
			 				+guest+"</a></td>");
			 out.println("<td align='left' width='100'><a href='message_add.jsp?username="
					    +URLEncoder.encode(guest, "UTF-8")+"' style='font-size:12px'>¸øËû(Ëı)ÁôÑÔ</a></td>");
			 out.println("<td width='250'><a style='font-size:12px;'>ÁôÑÔÊ±¼ä£º"+message_date+"</a></td>");
			 out.println("</tr>");
			 out.println("<tr>");
			 out.println("<td colspan='3' align='left'>"+message_content+"</td>");
			 out.println("</tr>");
		 	 out.println("</table>");
		 	 out.println("</div>");

		 	 if(message_reply!=null && message_reply.size()>0)
		 	 {
		 		 for(int j=0;j<message_reply.size();j++)
		 		 {
		 			 if(message_reply.get(j).getMessageid()==message_id)
		 			 {
		 				 MessageReply messagereply=message_reply.get(j);
							out.println("<table border='1' width='498' bgcolor='#FFFF80' bordercolor='#000000' style='margin-left:75px;border-collapse: collapse;'>");
							out.println("<tr>");
							out.println("<td width='70' align='center' style='font-size:12px'>");
							//out.println("");
							out.println("<img src='upload/Ïà²á/"+messagereply.getUsername()+"/host/me.jpg' width='60' height='60''/><br>");
							out.println("<a>"+messagereply.getMessagereplydate()+"</a><br>");
							out.println("</td>");
							out.println("<td valign='center' align='left'>");
							out.println(messagereply.getMessagereplycontent());
							out.println("</td>");
							out.println("</tr>");
							out.println("</table>");
							out.println("<div style='margin-left:85px;'><a href='DeleteMessageReply?messsage_reply_id="+messagereply.getMessagereplyid()+"&currpage="+curr_page+"&currstep="+curr_step+"'>É¾³ı»Ø¸´</a></div>");
		 			 }
		 		 }
		 	 }
		 	 out.println("<div align='right' class='link_style'><a href='javascript:showMessageReplyTextarea("+message_id+","+curr_page+","+curr_step+")'>»Ø¸´ÁôÑÔ</a><a href='DeleteMessage?message_id="+message_id+"&currpage="+curr_page+"&currstep="+curr_step+"'>É¾³ıÁôÑÔ</a></div>");
		 	 out.println("<div id='message_reply_textarea"+message_id+"'></div>");
		 	 out.println("</div>");//end of border
	         out.println("</div>");//end of single_list
    	}
    	else{
    		break;
		}
	}//end of for
	/*
    out.println("<div align='center' id='pages'>");
    if(countpage>pagestep)
    {
    	out.println("<a href='ajax_message.jsp?page="+(pagestep*(currstep-1))+"&step="+(currstep-1)+"'>&laquo;</a>");
    	for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
    	{
    		out.println("<a href='ajax_message.jsp?page="+k+"&step="+currstep+"'>"+k+"</a>");
    	}
    	int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
    	out.println("<a href='ajax_message.jsp?page="+max+"&step="+(currstep+1)+"'>&raquo;</a>");	
    }else{
    	if(countpage>1)
    	{
    		for(int k=1;k<=countpage;k++)
    		{
    			out.println("<a href='ajax_message.jsp?page="+k+"'>"+k+"</a>");
    		}
    	}
    }
    out.println("</div>");	
    */
	    out.println("<div align='center' id='pages'>");
	    if(countpage>pagestep)
	    {
	    	out.println("<a href='message_manage.jsp?page="+(pagestep*(currstep-1))+"&step="+(currstep-1)+"'>&laquo;</a>");
	    	for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
	    	{
	    		out.println("<a href='message_manage.jsp?page="+k+"&step="+currstep+"'>"+k+"</a>");
	    	}
	    	int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
	    	out.println("<a href='message_manage.jsp?page="+max+"&step="+(currstep+1)+"'>&raquo;</a>");	
	    }else{
	    	if(countpage>1)
	    	{
	    		for(int k=1;k<=countpage;k++)
	    		{
	    			out.println("<a href='message_manage.jsp?page="+k+"'>"+k+"</a>");
	    		}
	    	}
	    }
	    out.println("</div>");	
}else{
	out.println("<div align='center'><a class='success_style'>Äã»¹Ã»ÓĞÁôÑÔ!!!</a></div>");
}
%>