<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>���Ų��ͣ��û�ע��</title>
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
            <legend style="color: red;">������Ϣ</legend>
             <div>
              <form action="" method="post" name="Form">
                <table width="500" border="0">
                  <tr>
                    <td width="90">�û�����</td>
                    <td><input type="text" name="username" onfocus="clearUsernameText()"/><a href="javascript:sendCheckUsernameRequest()" style="text-decoration: none;font-size: 14px">[����û���]</a><label id="username"></label></td>
                  </tr>
                  <tr>
                    <td>���룺</td>
                    <td><input type="text" name="password" /></td>
                  </tr>
                  <tr>
                    <td>ȷ�����룺</td>
                    <td><input type="text" name="confirmpassword" onblur="checkPassword()"/><label id="confirmpassword"></label></td>
                  </tr>
                  <tr>
                  <tr>
                    <td>���䣺</td>
                    <td><input type="text" name="mail" onblur="checkMail()"/><label id="mail"></label></td>
                  </tr>
                  <tr>
                    <td>�Ա�</td>
                    <td>
                      <input type="radio" name="sex" value="��" onblur="clearSexText()"/> ��
                      <input type="radio" name="sex"  value="Ů" onblur="clearSexText()"/>Ů<label id="sex"></label>
                    </td>
                  </tr>
                  <tr>
                    <td>��֤�룺</td>
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
                    <td>���ڵ�����</td>
                    <td><input type="text" name="address" onblur="clearAddressText()"/><label id="address"></label></td>
                  </tr>
                  <tr>
                    <td><input type="hidden" name="regist_time" value="<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())%>"></td>
                    <td>
                      <input type="button" id="register" value="ע��" onclick="checkRegist()"/>
                      <input type="reset" id="reset" value="����" />
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
