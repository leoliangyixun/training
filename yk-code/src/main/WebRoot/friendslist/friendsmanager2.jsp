<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<jsp:useBean id="d" class="com.yangkai.bean.FriendsList" scope="session"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title>好友通讯录</title>
	<style type="text/css">
	#nav_second{}
	#nav_second input{ margin-bottom:10px; margin-top:10px;}
	#nav_second select{ margin-bottom:10px; margin-top:10px;}
	</style>
  </head>
  
  <body>
  <%
  ResultSet rs=null;
  String sql;
  String str;
  String username;
  String tel;
  int n;
  int mark;
  %>
  <table border="1" align="center" width="500">
  <tr>
    <td colspan="2" align="center">好友通讯录</td>
  </tr>
  <tr>
    <td width="242" align="center">姓名</td>
    <td width="242" align="center">电话/QQ/E-mail</td>
  </tr>
  <%
  try{
  mark=Integer.parseInt(request.getParameter("num"));
  }
  catch(Exception e){
	  mark=0;
  }
	switch(mark)  
	{	
	default:
		sql="SELECT * FROM callinfo";
		rs=d.myexecuteQuery(sql);
		while(rs.next())
		{
			out.println("<tr><td align=center>"+rs.getString(1)+"</td><td align=center>"+rs.getString(2)+"</td></tr>");
		}
		rs.close();
		break;
	case 1:
		username=d.setCharactorEncoding(request.getParameter("queryusername"));
		sql="SELECT * FROM callinfo WHERE  name LIKE '%"+username+"%'";
		rs=d.myexecuteQuery(sql);
		while(rs.next())
		{
			out.println("<tr><td align=center>"+rs.getString(1)+"</td><td align=center>"+rs.getString(2)+"</td></tr>");
		}
		rs.close();
		break;
	case 2:
		username=d.setCharactorEncoding(request.getParameter("delusername"));
		sql="DELETE FROM callinfo WHERE  name='"+username+"'";
		n=d.myexecuteUpdate(sql);
		if(n>0)
		{
			out.println("<tr><td colspan=2 align=center>删除成功。</td></tr>");
		}
		else
		{
			out.println("<tr><td colspan=2 align=center>删除失败！！！</td></tr>");
		}
		break;
	case 3:
		username=d.setCharactorEncoding(request.getParameter("addusername"));
		tel=request.getParameter("addtel");
		sql="INSERT INTO callinfo(name,contract) VALUES(	'"+username+"','"+tel+"')";
		n=d.myexecuteUpdate(sql);
		if(n>0)
		{
		str="SELECT * FROM callinfo WHERE name='"+username+"'";
		rs=d.myexecuteQuery(str);
		while(rs.next())
		{
			out.println("<tr><td align=center>"+rs.getString(1)+"</td><td align=center>"+rs.getString(2)+"</td></tr>");
		}
		rs.close();
		}
		else
		{
			out.println("<tr><td colspan=2 align=center>添加失败！！！</td></tr>");
		}
		break;
	case 4:
		username=d.setCharactorEncoding(request.getParameter("updateusername"));
		tel=request.getParameter("updatetel");
		sql="UPDATE callinfo SET contract='"+tel+"'WHERE name='"+username+"'";
		n=d.myexecuteUpdate(sql);
		if(n>0)
		{
			str="SELECT * FROM callinfo WHERE name='"+username+"'";
			rs=d.myexecuteQuery(str);
			while(rs.next())
			{
				out.println("<tr><td align=center>"+rs.getString(1)+"</td><td align=center>"+rs.getString(2)+"</td></tr>");
			}
			rs.close();
		}
		else
		{
			out.println("<tr><td colspan=2 align=center>修改失败！！！</td></tr>");
		}
		break;
	}
  %>
</table>

<table width="500" border="1" align="center">
  <tr>
    <td colspan="2" align="center">好友管理</td>
  </tr>
  <tr>
    <td width="242">
    <form method="post" action="friendsmanager2.jsp">
      <input type="hidden" name="num" value="1" />
      <input name="queryusername" type="text" value="填写姓名"/>
      <input type="submit" name="button" value="查询" />
    </form>
    </td>
    <td width="242"> 
    <form method="post" action="friendsmanager2.jsp">
      <input type="hidden" name="num" value="2" />
      <input name="delusername" type="text" value="填写姓名" />
      <input type="submit" name="button2" value="删除" />
    </form>
    </td>
  </tr>
  <tr id="nav_second">
    <td height="83" align="left" valign="middle">
    <form method="post" action="friendsmanager2.jsp">
      <input type="hidden" name="num" value="3" />
      <input name="addusername" type="text" value="填写姓名" />
      <input name="addtel" type="text" value="填写联系方式" />
	  <input type="submit" value="添加" />
    </form>
    </td>
    <td align="left" valign="middle">
     <form method="post" action="friendsmanager2.jsp">
     <input type="hidden" name="num" value="4" />
     <select name="updateusername">
       <option value="姓名">--选择姓名--</option>
       <%
       ResultSet username_rs=null;
       String usernamestr="SELECT name FROM callinfo";
       username_rs=d.myexecuteQuery(usernamestr);
       while(username_rs.next())
       {
       		out.print("<option value="+username_rs.getString(1).toString()+">"+username_rs.getString(1).toString()+"</option>");  		
       }
      username_rs.close();
      %>
     </select><br />
       <input name="updatetel" type="text" value="填写联系方式" />
      <input type="submit" value="修改"/>
    </form>
</td>
  </tr>
</table>  
  </body>
</html>
