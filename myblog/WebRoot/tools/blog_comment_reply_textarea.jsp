<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String blog_id=request.getParameter("blog_id");
	String blog_comment_id=request.getParameter("blog_comment_id");
	String mark=request.getParameter("mark");
	out.println("<div align='center' style='margin:5px 0 5px 65px'>");
	out.println("<form name='replyForm' action='AddBlogCommentReply' method='post'>");
	out.println("<input type='hidden' name='blog_id' value='"+blog_id+"'>");
	out.println("<input type='hidden' name='blog_comment_id' value='"+blog_comment_id+"'>");
	out.println("<input type='hidden' name='mark' value='"+mark+"'>");
	out.println("<input type='hidden' name='blog_comment_reply_date' value='"
				+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"'>");
	out.println("<div><textarea id='textarea_border' name='blog_comment_reply_content' cols='70' rows='5'></textarea></div>");
	//隐藏域要放在一起吗？
	out.println("<div align='left'>");
	out.println("<input type='submit' value='回复' style='margin:2px 0 0 5px;'>");
	out.println("<input type='reset' value='取消'>");
	out.println("</div>");
	/*****************************测试参数是否传递成功*****************************/
    //out.println("blog_id:"+blog_id);
	//out.println("blog_comment_id:"+blog_comment_id);
	//out.println("mark:"+mark);
	out.println("</form>");
	out.println("</div>");
%>

