<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
	String message_id=request.getParameter("message_id");
    String currpage=request.getParameter("page");
    String currstep=request.getParameter("step");
    String message_link_mark=request.getParameter("message_link_mark");
    String guest=request.getParameter("guest");
	out.println("<div align='left' style='margin-left:120px'>");
	out.println("<form name='replyForm' action='AddMessageReply' method='post'>");
	out.println("<input type='hidden' name='page' value='"+currpage+"'>");
	out.println("<input type='hidden' name='step' value='"+currstep+"'>");
	out.println("<input type='hidden' name='message_id' value='"+message_id+"'>");
	out.println("<input type='hidden' name='message_link_mark' value='"+message_link_mark+"'>");
	out.println("<input type='hidden' name='message_reply_date' value='"
				+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"'>");
	out.println("<div><textarea class='textarea' name='message_reply_content' cols='46' rows='3'></textarea></div>");
	out.println("<div align='center'>");
	out.println("<input type='submit' value='����ظ�' class='button_style' >");
	out.println("<input type='reset' value='ȡ���ظ�'>");
	out.println("</div>");
	/*****************************���Բ����Ƿ񴫵ݳɹ�*****************************/
    //out.println("message_id:"+message_id);
	//out.println("currpage:"+currpage);
	//out.println("currstep:"+currstep);
	//out.println("guest:"+guest);
	out.println("</form>");
	out.println("</div>");
%>

