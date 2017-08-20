<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>

<%
	String mood_id=request.getParameter("blog_id");
	String mood_comment_id=request.getParameter("blog_comment_id");
	out.println("<div align='center'>");
	out.println("<form name='replyForm' action='AddBlogCommentReply' method='post'>");
	out.println("<input type='hidden' name='mood_id' value='"+mood_id+"'>");
	out.println("<input type='hidden' name='mood_comment_id' value='"+mood_comment_id+"'>");
	out.println("<input type='hidden' name='mood_comment_reply_date' value='"
				+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"'>");
	out.println("<div><textarea class='textarea' name='mood_comment_reply_content' cols='60' rows='5'></textarea></div>");
	out.println("<div align='center'>");
	out.println("<input type='submit' value='发表回复' class='button_style' >");
	out.println("<input type='reset' value='取消回复'>");
	out.println("</div>");
	/*****************************测试参数是否传递成功*****************************/
    //out.println("blog_id:"+mood_id);
	//out.println("blogcomment_id:"+mood_comment_id);
	out.println("</form>");
	out.println("</div>");
%>

