<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String currpage=request.getParameter("page");
	String currstep=request.getParameter("step");
	String mood_comment_id=request.getParameter("mood_comment_id");
	out.println("<div align='center' style='margin:5px 0 5px 100px'>");
	out.println("<form name='replyForm' action='AddMoodCommentReply' method='post'>");
	out.println("<input type='hidden' name='page' value='"+currpage+"'>");
	out.println("<input type='hidden' name='step' value='"+currstep+"'>");
	out.println("<input type='hidden' name='mood_comment_id' value='"+mood_comment_id+"'>");
	out.println("<input type='hidden' name='mood_comment_reply_date' value='"
				+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"'>");
	out.println("<div><textarea id='textarea_border' name='mood_comment_reply_content' cols='64' rows='2'></textarea></div>");
	out.println("<div align='left'>");
	out.println("<input type='submit' value='回复'  style='margin-left:5px;'>");
	out.println("<input type='reset' value='取消'>");
	out.println("</div>");
	out.println("</form>");
	out.println("</div>");
%>

