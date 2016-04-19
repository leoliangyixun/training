<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String currpage=request.getParameter("page");
	String currstep=request.getParameter("step");
	String mood_id=request.getParameter("mood_id");
	out.println("<div align='center' style='margin:5px 0 5px 65px'>");
	out.println("<form name='replyForm' action='AddMoodComment' method='post'>");
	out.println("<input type='hidden' name='page' value='"+currpage+"'>");
	out.println("<input type='hidden' name='step' value='"+currstep+"'>");
	out.println("<input type='hidden' name='mood_id' value='"+mood_id+"'>");
	out.println("<input type='hidden' name='mood_comment_date' value='"
					+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"'>");
	out.println("<div><textarea  id='textarea_border' name='mood_comment_content' cols='70' rows='2'></textarea></div>");
	//out.println("<div><textarea style='border: #E97B56 solid 1px;font-size: 12px;' name='mood_comment_content' cols='80' rows='2'></textarea></div>");			
	out.println("<div align='left'>");
	out.println("<input type='submit' value='发表'  style='margin-left:5px;'>");
	out.println("<input type='reset' value='取消' style='margin-left:5px;'>");
	out.println("</div>");
	out.println("</form>");
	out.println("</div>");
%>

