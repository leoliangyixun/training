<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<jsp:useBean id="q" class="com.yangkai.bean.PagesApartMSSQLServer" scope="session"></jsp:useBean>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>ͼ����ϸ��Ϣ</title>
  </head>
  
  <body>
  <% 
	if(session.isNew())
	{
		out.print("�Ƿ��û�����ֹ����");
	}
	else{
	    ResultSet detail;
	    request.setCharacterEncoding("gb2312");
	    String id=request.getParameter("bookid");
	    int num=Integer.parseInt(id);
	    String str="SELECT * FROM bookinfo WHERE id ="+num+"";	  
		detail=q.execute(str);   
		out.print("<table>");
		out.print("<tr>");
		while(detail.next())
		{
			out.print("<td>"+detail.getString(2)+"</td>");
		}
		out.print("</tr>");
		out.print("</table>");
		detail.close();
		}
  %>
</body>
</html>