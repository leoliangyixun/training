<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>枫雅博客：用户注册</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/myblog.js"></script>
<style type="text/css">
#regist {
	width:620px;
	margin:20px auto;
}
#legend{
	margin:10px auto;width: 500px; 
	/*border: #000000 solid 1px;*/ 
}
#legend tr {
	text-align:left;
}
#reset{ 
	width: 51px;
	height: 20px;
	border: none;
	text-indent: -99999px; 
	background-image:url(../css/images/reset.gif);
}
#register{ 
	width: 51px;
	height: 20px;
	border: none;
	text-indent: -99999px; 
	background-image:url(../css/images/register.gif);margin-right:5px;
}
</style>
</head>

<body>
<div>
<div id="nav"><jsp:include page="../include/regist_logo.jsp" flush="true"></jsp:include></div>
<div id="regist">
  
  <div id="list_top">
    <div id="list_middle">
      <div id="list_bottom">
        <div>
          <fieldset id="legend">
            <legend style="color: red;">基本信息</legend>
             <div>
              <form action="" method="post" name="Form">
                <table width="500" border="0">
                  <tr>
                    <td width="90">用户名：</td>
                    <td><input type="text" name="username" onfocus="clearUsernameText()"/><a href="javascript:sendCheckUsernameRequest()" style="text-decoration: none;font-size: 14px">[检测用户名]</a><label id="username"></label></td>
                  </tr>
                  <tr>
                    <td>密码：</td>
                    <td><input type="text" name="password" /></td>
                  </tr>
                  <tr>
                    <td>确认密码：</td>
                    <td><input type="text" name="confirmpassword" onblur="checkPassword()"/><label id="confirmpassword"></label></td>
                  </tr>
                  <tr>
                  <tr>
                    <td>邮箱：</td>
                    <td><input type="text" name="mail" onblur="checkMail()"/><label id="mail"></label></td>
                  </tr>
                  <tr>
                    <td>性别：</td>
                    <td>
                      <input type="radio" name="sex" value="男" onblur="clearSexText()"/> 男
                      <input type="radio" name="sex"  value="女" onblur="clearSexText()"/>女<label id="sex"></label>
                    </td>
                  </tr>
                  <tr>
                    <td>验证码：</td>
                    <td>
                    <%
            		String num=null;
                    StringBuffer regist_num=new StringBuffer();
            		for(int i=0;i<4;i++)
            		{
            			regist_num.append(new Random().nextInt(10));
            		}
            		num=regist_num.toString();
                    %>
                    <input type="text" name="num" onblur="checkNum(<%=num%>)"/><%=num%><label id="num"></label>
                    </td>
                  </tr>
                  <tr>
                    <td>所在地区：</td>
                    <td><input type="text" name="address" onblur="clearAddressText()"/><label id="address"></label></td>
                  </tr>
                  <tr>
                    <td><input type="hidden" name="regist_time" value="<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())%>"></td>
                    <td>
                      <input type="button" id="register" value="注册" onclick="checkRegist()"/>
                      <input type="reset" id="reset" value="重置" />
                    </td>
                  </tr>
                </table>
              </form>
            </div>
          </fieldset>
        </div>
      </div>
    </div>
  </div>
  
</div>
	<jsp:include page="../include/bottom.html" flush="true"></jsp:include>
</div>
</body>
</html>
