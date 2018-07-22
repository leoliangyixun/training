<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<jsp:useBean id="d" class="com.yangkai.bean.FriendsListPagesDisplay" scope="session"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>好友通讯录</title>
	<style type="text/css">
	#nav_second{}
	#nav_second input{ margin-bottom:10px; margin-top:10px;}
	#nav_second select{ margin-bottom:10px; margin-top:10px;}
	.link_a{margin-right:20px; }
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
  int $total=0;
  int $percount=3;
  int $currpage=0;
  int $position=0;
  int $pagecount=0;
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
		out.println("<tr><td colspan=2 align=center>欢迎使用友友通讯录O(∩_∩)O</td></tr>");
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
	case 5:
		sql="SELECT * FROM callinfo";
		rs=d.myexecuteQuery(sql);
		if(rs.isAfterLast()==rs.isBeforeFirst())
		{
			out.println("<tr><td colspan=2 align=center>通讯录为空</td></tr>");
		}
		else{
			 
			String $curr_page=request.getParameter("page");
			if($curr_page==null)
			{
				$currpage=1;
			}
			else{
				$currpage=Integer.parseInt($curr_page);
			}
			rs.last();
			$total=rs.getRow();
			$position=$percount*($currpage-1)+1;
			if($total % $percount==0)
			{
				$pagecount=$total / $percount;
			}
			else{
				$pagecount=$total / $percount + 1;
			}
			if($currpage>$pagecount)
			{
				out.println("<tr><td colspan=2 align=center>已经是最后一页</td></tr>");
			}
			else{
			rs.absolute($position);
			for(int i=0;i<$percount;i++)
			{
				out.println("<tr><td align=center>"+rs.getString(1)+"</td><td align=center>"+rs.getString(2)+"</td></tr>");
				rs.next();
				if(rs.isAfterLast())
				break;
			}
			out.println("<tr><td colspan=2 align=center>【共"+$pagecount+"页】/【当前页:"+$currpage+"】/【共"+$total+"条记录】</td></tr>");
			}
		}
		%>
<tr>
  <td colspan="2" align="center">
  
  <input type="button" value="第一页" id="first" onclick="window.location.href='friendslistpagesdisplay.jsp?page=1&num=<%=mark%>'"/>
  <input type="button" value="上一页" id="up" onclick="window.location.href='friendslistpagesdisplay.jsp?page=<%=$currpage-1%>&num=<%=mark%>'"/>
  <input type="button" value="下一页" id="down" onclick="window.location.href='friendslistpagesdisplay.jsp?page=<%=$currpage+1%>&num=<%=mark%>'"/>
  <input type="button" value="最末页" id="last" onclick="window.location.href='friendslistpagesdisplay.jsp?page=<%=$pagecount%>&num=<%=mark%>'"/>
  </td>
  </tr>
		<%
		rs.close();
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
    <form method="post" action="friendslistpagesdisplay.jsp">
      <input type="hidden" name="num" value="1" />
      <input name="queryusername" type="text" value="填写姓名"/>
      <input type="submit" name="button" value="查询" />
    </form>
    </td>
    <td width="242"> 
    <form method="post" action="friendslistpagesdisplay.jsp">
      <input type="hidden" name="num" value="2" />
      <input name="delusername" type="text" value="填写姓名" />
      <input type="submit" name="button2" value="删除" />
    </form>
    </td>
  </tr>
  <tr id="nav_second">
    <td height="83" align="left" valign="middle">
    <form method="post" action="friendslistpagesdisplay.jsp">
      <input type="hidden" name="num" value="3" />
      <input name="addusername" type="text" value="填写姓名" />
      <input name="addtel" type="text" value="填写联系方式" />
	  <input type="submit" value="添加" />
    </form>
    </td>
    <td align="left" valign="middle">
     <form method="post" action="friendslistpagesdisplay.jsp">
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
 <tr>
    <td colspan="2" align="center" valign="middle">
    <form method="post" action="friendslistpagesdisplay.jsp">
    <input type="hidden" name="num" value="5" />
    <input type="submit" value="查询所有通讯录" />
    </form>
    </td>
  </tr>
</table> 
<%//d.closeConnection(); %>
  </body>
</html>
