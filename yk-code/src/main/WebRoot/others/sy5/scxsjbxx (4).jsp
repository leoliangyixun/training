<%@page contentType="text/html;charset=GB2312"%>
<%@include file="jdbc-xsjbxx.jsp"%>

<%
String xuehao = request.getParameter("xuehao");
String sql = "delete from xsjbxx where xuehao= '"+xuehao+"'";
s.executeUpdate(sql);
s.close();
c.close();
out.print("<script language='javascript'>alert('ѧ��"+xuehao+"�Ļ�����Ϣ�ѳɹ�ɾ����');window.navigate('index.htm');</script>");
%>