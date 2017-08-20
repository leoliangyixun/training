<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	
    String currpage=request.getParameter("page");
    String currstep=request.getParameter("step");
	out.println("<div align='left' style='margin-left:120px'>");
	out.println("<form name='replyForm' action='AddMessage' method='post'>");
	out.println("<input type='hidden' name='page' value='"+currpage+"'>");
	out.println("<input type='hidden' name='step' value='"+currstep+"'>");
	
	out.println("<input type='hidden' name='message_reply_date' value='"
				+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"'>");
	out.println("<div><textarea class='textarea' name='message_content' cols='20' rows='3'></textarea></div>");
	out.println("<div align='center'>");
	out.println("<input type='submit' value='添加留言' class='button_style' >");
	out.println("<input type='reset' value='清除'>");
	out.println("</div>");
	out.println("</form>");
	out.println("</div>");
%>

