<%@ page language="java" import="java.util.*,java.sql.*,java.io.*" pageEncoding="gb2312"%>
<jsp:useBean id="vip" class="com.yangkai.bean.LoginCheck" scope="page"></jsp:useBean><!-- �����scope���Ժ���Ҫ -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'LoginCheck.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
   <form method="post" action="" >
  <table width="259" align="center" >
    <caption>��Ա��¼ </caption>
    <tr>
      <td width="79">�û�����</td>
      <td width="164">
      <input type="text" name="user" /></td>
    </tr>
    <tr>
      <td>��&nbsp;&nbsp;�룺</td>
      <td>
      <input type="text" name="password" /></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><input type="submit"  value="�ύ" />
      <input type="reset" value="����" /></td>
    </tr>
  </table>
</form>
<%
request.setCharacterEncoding("gb2312");
String user=request.getParameter("user");
String password=request.getParameter("password");
//out.print(user);
Boolean Result=false;
if(user==null && password==null)
	  out.print("");
//if(request.getSession()==null)
//	out.print("");
else{
	  Result=vip.checkManager(user);	
if(Result==true)
	out.print("<center>��¼�ɹ�</center>");
else
	out.print("<center>�û������������</center>");
}
%>
  </body>
</html>
