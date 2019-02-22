<%@ page language="java" import="java.util.*,java.sql.*,java.io.*" pageEncoding="gb2312"%>
<jsp:useBean id="vip" class="com.yangkai.bean.LoginCheck" scope="page"></jsp:useBean><!-- 这里的scope属性很重要 -->
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
    <caption>会员登录 </caption>
    <tr>
      <td width="79">用户名：</td>
      <td width="164">
      <input type="text" name="user" /></td>
    </tr>
    <tr>
      <td>密&nbsp;&nbsp;码：</td>
      <td>
      <input type="text" name="password" /></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><input type="submit"  value="提交" />
      <input type="reset" value="重置" /></td>
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
	out.print("<center>登录成功</center>");
else
	out.print("<center>用户名或密码错误</center>");
}
%>
  </body>
</html>
