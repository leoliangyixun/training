<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<jsp:useBean id="d" class="com.yangkai.bean.FriendsList" scope="session"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>好友通讯录</title>
<style type="text/css">
#nav_second{}
#nav_second input{ margin-bottom:10px; margin-top:10px;}
#nav_second select{ margin-bottom:10px; margin-top:10px;}

</style>
	

  </head>
  
  <body>
<table border="1" align="center" width="500">
  <tr>
    <td colspan="2" align="center">好友通讯录</td>
  </tr>
  
  <tr>
    <td width="242" align="center">姓名</td>
    <td width="242" align="center">电话/QQ/E-mail</td>
  </tr>
</table>

<table width="500" border="1" align="center">
  <tr>
    <td colspan="2" align="center">好友管理</td>
  </tr>
  <tr>
    <td width="242">
    <form method="post" action="">
      <input name="username" type="text" value="填写姓名"/>
      <input type="submit" name="button" value="查询" />
    </form>
    </td>
    <td width="242"> 
    <form method="post" action="">
      <input name="delete" type="text" value="填写姓名" />
      <input type="submit" name="button2" value="删除" />
    </form>
    </td>
  </tr>
  <tr id="nav_second">
    <td height="83" align="left" valign="middle">
    <form method="post" action="">
      <input name="add" type="text" value="填写姓名" />
      <input name="tel" type="text" value="填写联系方式" />
	  <input type="submit" name="button3" value="添加" />
    </form>
    </td>
    <td align="left" valign="middle">
     <form method="post" action="">
      <select name="class">
     <option value="通讯录类别">--通讯录类别--</option>
     <%
       ResultSet class_rs=null;
       String classstr="SELECT DISTINCT(class) FROM calldetails";
       class_rs=d.myexecuteQuery(classstr);
       while(class_rs.next())
       {
       		out.print("<option value="+class_rs.getString(1).toString()+">"+class_rs.getString(1).toString()+"</option>");  		
       }
       class_rs.close();
      %>
     </select>
     <select name="username">
       <option value="姓名">--选择姓名--</option>
       <%
       ResultSet username_rs=null;
       String usernamestr="SELECT name FROM calldetails";
       username_rs=d.myexecuteQuery(usernamestr);
       while(username_rs.next())
       {
       		out.print("<option value="+username_rs.getString(1).toString()+">"+username_rs.getString(1).toString()+"</option>");  		
       }
       username_rs.close();
      %>
     </select><br />
       <input name="tel" type="text" value="填写联系方式" />
      <input type="submit" value="修改"/>
    </form>
</td>
  </tr>
</table>

</body>
</html>

