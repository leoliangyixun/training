<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String currpage=request.getParameter("page");
	String currstep=request.getParameter("step");
	String response_id=request.getParameter("response_id");
	out.println("<div align='center' style='margin:5px 0 5px 90px'>");
	out.println("<div align='left' style='margin-left:8px'>请输入验证信息：</div>");
	out.println("<form name='requestForm' action='AddUserRequest' method='post'>");
	out.println("<input type='hidden' name='page' value='"+currpage+"'>");
	out.println("<input type='hidden' name='step' value='"+currstep+"'>");
	out.println("<input type='hidden' name='response_id' value='"+response_id+"'>");
	out.println("<input type='hidden' name='request_date' value='"
					+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"'>");
	out.println("<div><textarea  id='textarea_border' name='request_content' cols='66' rows='2'></textarea></div>");
	out.println("<div align='left'>");
	out.println("<input type='submit' value='确定'  style='margin-left:5px;'>");
	out.println("<input type='reset' value='清除' style='margin-left:5px;'>");
	out.println("</div>");
	out.println("</form>");
	out.println("</div>");
%>

