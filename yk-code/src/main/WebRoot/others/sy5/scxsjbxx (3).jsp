<%@page contentType="text/html;charset=GB2312"%>
<%@include file="jdbc-xsjbxx.jsp"%>

<%
String xuehao = request.getParameter("xuehao");
String sql = "delete from xsjbxx where xuehao= '"+xuehao+"'";
s.executeUpdate(sql);
s.close();
c.close();
out.print("<script language='javascript'>alert('学号"+xuehao+"的基本信息已成功删除！');window.navigate('index.htm');</script>");
%>