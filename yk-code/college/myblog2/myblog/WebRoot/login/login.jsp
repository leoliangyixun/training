<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>

<title>枫雅博客：用户登录</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/myblog.js"></script>
<style type="text/css">
#login {
	background: #F9F0CF url(../css/images/login.png) no-repeat;
	width: 654px;
	height: 295px;
	margin: 20px auto;
	border: 1px solid #D1B681;
}

#login .login_style {
	margin-top: 150px;
	margin-left: auto;
	margin-right: auto;
}
#login .login_label {
	margin: 10px auto;
	width: 220px; /*border:red solid 2px;*/
}
#submit{ 
	width: 51px;
	height: 20px;
	border: none;
	text-indent: -99999px; 
	background-image:url(../css/images/submit.gif);
} 
#reset{ 
	width: 51px;
	height: 20px;
	border: none;
	text-indent: -99999px; 
	background-image:url(../css/images/reset.gif);
}
.input {
	font-family: "宋体";
	font-size: 9pt;
	background-color: #DFD4A7;
	border: 1px solid #D1B681;
}

img {
	border: 0;
}
</style>
</head>

<body>

	<div>
		<div id="nav"><jsp:include page="../include/login_logo.jsp" flush="true"></jsp:include></div>
		<div id="login">
			<div class="login_style">
				<form name="loginForm" method="post" action="../LoginCheck">
					<table width="220" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td width="70" height="30">用户名：</td>
							<td width="220"><input name="username" type="text" class="input" id="account" size="25"></td>
						</tr>
						<tr>
							<td height="30">密&nbsp;&nbsp;&nbsp;码：</td>
							<td><input name="password" type="text" class="input" id="password" size="25"></td>	
						</tr>
						<tr>
						 	<td><input type="hidden" name="login_mark"value="<%=Constants.BLOG_LOGIN_MARK_FROM_LOGIN%>" /></td>
						 	<td height="30" align="center">
								<!--a href="javascript:login()"><img src="css/images/submit.gif"></a--> 
								<!--a href="javascript:reset()"><img src="css/images/reset.gif"></a-->
	            				<input type="submit" value="登陆" id="submit"/> 
		       				    <input type="reset" value="取消" id="reset"/> 
							</td>
						</tr>
					</table>	
				</form>
			</div>
			<div id="fail" align="center" class="login_label"></div>
			<%
			//这段代码的位置很重要。  
			String login=(String)session.getAttribute("login");
			if(login!=null && login.equals("false"))//在用“==”，“equals” 等运算符之前要先确保运算符左边不能为null。
			{
				out.println("<script>checkLogin();</script>");
				session.removeAttribute("login");
			}
	       %>
		</div>
	</div>
	<jsp:include page="../include/bottom.html" flush="true"></jsp:include>
	</div>
</body>
</html>

