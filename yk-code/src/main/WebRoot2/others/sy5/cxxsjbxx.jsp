<%@page contentType="text/html;charset=GB2312"%> 
<%@include file="jdbc-xsjbxx.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>查询学生基本信息</title>
<!--引入样式文件-->
<link rel=stylesheet href="xsjbxx.css" type=text/css>
</head>

<body style="text-align: center">
<form method="POST" action="cxxsjbxx.jsp">
	<table border="1" width="95%" cellspacing="1" height="64" bordercolor="#000000">
		<tr>
			<td colspan="3"><a href="index.htm">主页</a>-〉查询学生基本信息</td>
		</tr>
      <tr>
         <td colspan="3"><p id=s1 align="center">
		   请输入查询条件</p></td>
      </tr>
		<tr>
			<td width="30%">查询方式：<input type="radio" value="xuehao" checked name="fangshi">学号<input type="radio" name="fangshi" value="xingming">姓名</td>
			<td width="39%">查询条件：<input type="text" name="tiaojian" size="20"></td>
			<td width="29%"><input type="submit" value="提交" name="B1"></td>
		</tr>
	</table>
</form>

<table border="1" width="95%" cellspacing="0" cellpadding="0" height="26" bordercolor="#000000" style="border-collapse: collapse">
	<tr>
			<td colspan="11">
			<div align="center">
				<b>满足查询条件的学生基本信息</b></div>
			</td>
		 </tr>
		 
			 <tr>
			 <td align=center>姓名</td>
			 <td align=center>学号</td>
  <td align=center>性别</td>
  <td align=center>出生日期</td>
  <td align=center>电子邮箱</td>
  <td align=center>血型</td>
  <td align=center>爱好</td>
  <td align=center>喜欢的运动</td>
  <td align=center>备注</td>
  <td align=center>是否修改</td>
  <td align=center>是否删除</td>
             </tr>
  <!--在这儿添加查询数据库信息的JSP脚本-->
<% 
//设置中文编码方式  
request.setCharacterEncoding("gb2312");

//接收查询方式的值
String fangshi=request.getParameter("fangshi");

//接收查询条件的值
String tiaojian=request.getParameter("tiaojian");
 
//执行查询SQL语句  
String sql = "select * from xsjbxx where "+fangshi+" like '%"+tiaojian+"%'";
ResultSet rs = s.executeQuery(sql);
 String xingming;
 String xuehao;
 String xingbie;
 
 String chushengriqi;
 String youxiang;
 String xuexing;
 
 String aihao;
 String tiyuyundong;
 String beizhu;
 
 //得到所有满足条件的查询结果
 while(rs.next())
 {
  xingming = rs.getString(1);
  xuehao = rs.getString(2);
  xingbie = rs.getString(3);
  chushengriqi = rs.getString(4);
  youxiang = rs.getString(5);
  xuexing = rs.getString(6);
  aihao = rs.getString(7);
  tiyuyundong = rs.getString(8);
  beizhu = rs.getString(9);
  %>
  
<!--在这儿显示查询出的数据库中的信息-->  		
	<tr align="center">
 <td align=center><%=xingming%></td>
  <td align=center><%=xuehao%></td>
   <td align=center><%=xingbie%></td> 
   <td align=center><%=chushengriqi%></td>
    <td align=center><%=youxiang%></td> 
    <td align=center><%=xuexing%></td> 
    <td align=center><%=aihao%></td> 
    <td align=center><%=tiyuyundong%></td> 
    <td align=center><%=beizhu%></td> 
    <td><a href="xgxsjbxx.jsp?xuehao=<%=xuehao%>">修改</a></td>
    <td><a href="scxsjbxx.jsp?xuehao=<%=xuehao%> ">删除</a></td>
	</tr>
<!--在这儿释放资源-->	
     <%
 }
 rs.close();
 s.close();
 c.close();
%>
</table>
</body>

</html>