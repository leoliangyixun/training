<%@page contentType="text/html;charset=GB2312"%> 
<%@include file="jdbc-xsjbxx.jsp"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>修改学生基本信息</title>
<!--引入信息检验文件-->
<script src="xsjbxx.js"  language="javascript"></script>
<!--引入样式文件-->
<link rel=stylesheet href="xsjbxx.css" type=text/css>
</head>
<%String xuehao=request.getParameter("xuehao");%>
<%
 String sql = "select * from xsjbxx where xuehao='"+xuehao+"'";
 ResultSet rs = s.executeQuery(sql);
 String xingming="";
 String xingbie="";
 
 String chushengriqi="";
 String youxiang="";
 String xuexing="";
 
 String aihao="";
 String tiyuyundong="";
 String beizhu="";

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
  } 
 rs.close();
 s.close();
 c.close(); 
 %> 
<body>
<form name=form1 onsubmit="checkform()" method="POST" action="zxxgxsjbxx.jsp">
	<div align="center">
		<table border="1" width="80%" id="table1" height="484" bordercolor="#000000" cellspacing="1" cellpadding="3">
			<tr>
				<td colspan="2">
				<a href="index.htm">主页</a>-〉<a href="cxxsjbxx.jsp">查询学生基本信息</a>-〉修改学生基本信息</td>
			</tr>
			<tr>
				<td colspan="2">
				<p align="center" id=s1 >修改学生基本信息</p></td>
			</tr>
			<tr>
				<td colspan="2">
				<p align="center">
				<font id=s3>（带**号为必填项目）</font></td>
			</tr>
			<tr>
				<td width="27%" id=s2>姓名</td>
				<td width="70%"><input type="text" name="xingming" size="20" value="<%=xingming%>"><font id=s3>**不多于4个汉字</font></td>
			</tr>
			<tr>
				<td width="27%" id=s2>学号</td>
				<!--属性readonly表示该文本框不可操作-->
				<td width="70%"><input type="text" name="xuehao" size="20" value="<%=xuehao%>" readonly><font id=s3>**学号不可修改</font></td>
			</tr>
			<tr>
				<td width="27%" id=s2>性别</td>
				<td width="70%">
				<!--这里使用了条件表达式，其语法格式为：（条件）？结果1：结果2，首先计算条件，返回值为true时，表达式的值为结果1，为false时，表达式的值为结果2。-->
				<input type="radio" value="男"  name="xingbie" <%=xingbie.indexOf("男")!=-1?"checked":"" %>>男 
				<input type="radio" name="xingbie" value="女" <%=xingbie.indexOf("女")!=-1?"checked":"" %>>女</td>
			</tr>
			<tr>
				<td width="27%" id=s2>出生日期</td>
				<td width="70%"><input type="text" name="chushengriqi" size="20" value="<%=chushengriqi%>"><font id=s3>**必须是有效日期，并符合“YYYY-MM-DD”格式</font></td>
			</tr>
			<tr>
				<td width="27%" id=s2>电子邮箱</td>
				<td width="70%"><input type="text" name="youxiang" size="20" value="<%=youxiang%>"><font id=s3>**不多于30个字符，字符中必须包含“@”和“.”符号，并且“@”符号既不能是电子邮箱的第一个字符，也不能是最后一个字符</font></td>
			</tr>
			<tr>
				<td width="27%" id=s2>血型</td>
				<td width="70%"><select size="1" name="xuexing">
				<option value="A" <%=xuexing.indexOf("A")!=-1?"selected":"" %>>A</option>
				<option value="B" <%=xuexing.indexOf("B")!=-1?"selected":"" %>>B</option>
				<option value="AB" <%=xuexing.indexOf("AB")!=-1?"selected":"" %>>AB</option>
				<option value="O" <%=xuexing.indexOf("O")!=-1?"selected":"" %>>O</option>
				</select></td>
			</tr>
			<tr>
				<td width="27%" id=s2>兴趣爱好</td>
				<td width="70%">
				<input type="checkbox" name="aihao" value="阅读" <%=aihao.indexOf("阅读")!=-1?"checked":"" %>>阅读 
				<input type="checkbox" name="aihao" value="音乐" <%=aihao.indexOf("音乐")!=-1?"checked":"" %>>音乐 
				<input type="checkbox" name="aihao" value="美食" <%=aihao.indexOf("美食")!=-1?"checked":"" %>>美食 
				<input type="checkbox" name="aihao" value="运动" <%=aihao.indexOf("运动")!=-1?"checked":"" %>>运动 
				<input type="checkbox" name="aihao" value="旅游" <%=aihao.indexOf("旅游")!=-1?"checked":"" %>>旅游</td>
			</tr>
			<tr>
				<td width="27%" style="font-weight:bold;color:black;font-size:18;text-align:right;letter-spacing:0.5em">喜欢的体育运动</td>
				<td width="70%"><select size="4" name="tiyuyundong" multiple>
				<option value="篮球" <%=tiyuyundong.indexOf("篮球")!=-1?"selected":"" %>>篮球</option>
				<option value="足球" <%=tiyuyundong.indexOf("足球")!=-1?"selected":"" %>>足球</option>
				<option value="羽毛球" <%=tiyuyundong.indexOf("羽毛球")!=-1?"selected":"" %>>羽毛球</option>
				<option value="乒乓球" <%=tiyuyundong.indexOf("乒乓球")!=-1?"selected":"" %>>乒乓球</option>
				<option value="轮滑" <%=tiyuyundong.indexOf("轮滑")!=-1?"selected":"" %>>轮滑</option>
				<option value="长跑" <%=tiyuyundong.indexOf("长跑")!=-1?"selected":"" %>>长跑</option>
				</select></td>
			</tr>
			<tr>
				<td width="27%" id=s2>备注</td>
				<td width="70%" id=s3><textarea rows="8" name="beizhu" cols="40"><%=beizhu%></textarea>（注：不多于20个汉字）</td>
			</tr>
			<tr>
				<td colspan="2">
				<p align="center">
				<input type="submit" value="提交" name="B1">&nbsp;
				<input type="reset" value="重置" name="B2"></td>
			</tr>
		</table>
	</div>
</form>
</body>
</html>