<%@page import="java.text.SimpleDateFormat"%>
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
	         String message_date =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(msg.getMessagedate());
	         out.println("<div id='single_list'>");
	         out.println("<div class='border'>");
	         out.println("<div>");
			 out.println("<table border='1' width='570' bgcolor='#FFFF80' bordercolor='#FFFFFF' style='margin:10px auto;border-collapse: collapse;'>");
			 out.println("<tr>");
			 out.println("<td rowspan='2' width='60' align='center' valign='middle' style='font-size:12px'>");
			 out.println("<img src='upload/Ïà²á/"+guest+"/image/me.jpg' width='60' height='60''/>");
			 out.println("</td>");
			 out.println("<td align='center' width='210'><a style='font-size:12px;margin-right:20px;'>"
			 				+guest+"</a></td>");
			 out.println("<td align='center' width='100'><a href='message_add.jsp?username="
					    +URLEncoder.encode(guest, "UTF-8")+"' style='font-size:12px'>ÁôÑÔ</a></td>");
			 out.println("<td width='200' align='center'><a style='font-size:12px;'>ÁôÑÔÊ±¼ä£º"+message_date+"</a></td>");
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
							out.println("<table border='1' width='508' bgcolor='#FFFF80' bordercolor='#FFFFFF' style='margin-left:65px;border-collapse: collapse;'>");
							out.println("<tr>");
							out.println("<td width='60' align='center' valign='middle' style='font-size:12px'>");
							//out.println("");
							out.println("<img src='upload/Ïà²á/"+messagereply.getUsername()+"/image/me.jpg' width='60' height='60''/><br>");
							out.println("<a>"+messagereply.getMessagereplydate()+"</a><br>");
							out.println("</td>");
							out.println("<td valign='center' align='left'>");
							out.println(messagereply.getMessagereplycontent());
							out.println("</td>");
							out.println("</tr>");
							out.println("</table>");
							out.println("<div style='margin-left:65px;'><a href='DeleteMessageReply?messsage_reply_id="+messagereply.getMessagereplyid()+"&page="+currpage+"&step="+currstep+"'>É¾³ı»Ø¸´</a></div>");
		 			 }
		 		 }
		 	 }
		 	 out.println("<div align='right' class='link_style'><a href='javascript:showMessageReplyTextarea("+message_id+","+currpage+","+currstep+")'>»Ø¸´ÁôÑÔ</a><a href='DeleteMessage?message_id="+message_id+"&page="+currpage+"&step="+currstep+"'>É¾³ıÁôÑÔ</a></div>");
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
    	out.println("<a href='javascript:findMyMessage("+(pagestep*(currstep-1))+","+(currstep-1)+")'>&laquo;</a>");
    	for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
    	{	
    		out.println("<a href='javascript:findMyMessage("+k+","+currstep+")'>"+k+"</a>");
    	}
    	int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
    	out.println("<a href='javascript:findMyMessage("+max+","+(currstep+1)+")'>&raquo;</a>");	
    }else{
    	if(countpage>1)
    	{
    		for(int k=1;k<=countpage;k++)
    		{
    			out.println("<a href='javascript:findMyMessage("+k+","+null+")'>"+k+"</a>");
    		}
    	}
    }
    out.println("</div>");	
}else{
	out.println("<div align='center'><a class='success_style'>»¹Ã»ÓĞÈË¸øÄãÁôÑÔ!!!</a></div>");
}
%>