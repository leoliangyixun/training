<%@page import="com.yangkai.myblog.domain.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	User bloger=(User)session.getAttribute("bloger");
%>
<div style="border:#E97B56 solid 1px;" align="center">
<form action="AlterUserImage" method="post"  enctype="multipart/form-data" name="form1" id="form1">
  <table width="570" border="0">
    <tr>
      <td width="300" class="user_about"><a>用户图像</a></td>
    </tr>
    <tr>
      <td align="left"><img src="upload/相册/<%=bloger.getUsername() %>/image/me.jpg" width="150" height="200"/></td>
    </tr>
    <tr>
      <td align="left"><input type="file" name="image" size="40"/>
      <input type="submit" name="button" id="button" value="修改图像" style="margin-right:20px" />
      <input type="reset" name="button2" id="button2" value="取消修改" /></td>
    </tr>
  </table>
</form>
</div>
