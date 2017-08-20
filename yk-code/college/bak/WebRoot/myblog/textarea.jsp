<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>

  <%
	String blog_id=request.getParameter("blog_id");
	String blogcomment_id=request.getParameter("blogcomment_id");
	String mark=request.getParameter("mark");
	out.println("<div align='center'>");
	out.println("<form name='replyForm' method='post'>");
	out.println("<input type='hidden' name='blog_id' value='"+blog_id+"'>");
	out.println("<input type='hidden' name='blogcomment_id' value='"+blogcomment_id+"'>");
	out.println("<input type='hidden' name='mark' value='"+mark+"'>");
	out.println("<input type='hidden' name='blogcommentreply_date' value='"
				+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+"'>");
	
	out.println("<div><textarea class='textarea' name='blogcommentreply_content' cols='60' rows='5'></textarea></div>");
	//隐藏域要放在一起吗？
    
	out.println("<div align='center'>");
	out.println("<input type='button' value='发表回复' class='button_style' onClick='submitReply()'>");
	out.println("<input type='reset' value='取消'>");
	out.println("</div>");
	/*****************************测试参数是否传递成功*****************************/
    out.println("blog_id:"+blog_id);
	out.println("blogcomment_id:"+blogcomment_id);
	out.println("mark:"+mark);
	out.println("</form>");
	out.println("</div>");
%>

