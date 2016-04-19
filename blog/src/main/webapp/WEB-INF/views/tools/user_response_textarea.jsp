<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String currpage=request.getParameter("page");
	String currstep=request.getParameter("step");
	String request_id=request.getParameter("request_id");
	out.println("<div align='center' style='margin:5px 0 5px 90px'>");
	out.println("<div align='left' style='margin-left:8px'><a style='margin-right:20px'>添加拒绝理由或</a><a href='#' onclick='javascript:document.responseForm.submit()'>直接拒绝</a></div>");
	out.println("<form name='responseForm' action='AddUserResponse' method='post'>");
	out.println("<input type='hidden' name='page' value='"+currpage+"'>");
	out.println("<input type='hidden' name='step' value='"+currstep+"'>");
	out.println("<input type='hidden' name='request_id' value='"+request_id+"'>");
	out.println("<input type='hidden' name='response_date' value='"
					+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"'>");
	out.println("<div><textarea  id='textarea_border' name='response_content' cols='66' rows='2'></textarea></div>");
	out.println("<div align='left'>");
	out.println("<input type='submit' value='确定'  style='margin-left:5px;'>");
	out.println("<input type='reset' value='清除' style='margin-left:5px;'>");
	out.println("</div>");
	out.println("</form>");
	out.println("</div>");
%>

