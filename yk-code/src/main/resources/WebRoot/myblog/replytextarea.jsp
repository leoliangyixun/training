<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@page import="java.text.SimpleDateFormat"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    
    <title>My JSP 'replytextarea.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link href="css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/myblog.js"></script>

  </head>
  
  <body>
  <%
	String blog_id=request.getParameter("blog_id");
	String blogcomment_id=request.getParameter("blogcomment_id");
	String mark=request.getParameter("mark");
	out.println("<div align='center'>");
	out.println("<form name='replyForm' method='post'");
	
	out.println("<input type='hidden' name='blogcomment_id' value='"+blogcomment_id+"'>");
	out.println("<input type='hidden' name='mark' value='"+mark+"'>");
	out.println("<input type='hidden' name='blogcommentreply_date' value='"
				+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+"'>");
	out.println("<input type='hidden' name='blog_id' value='"+blog_id+"'>");
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
  </body>
</html>
