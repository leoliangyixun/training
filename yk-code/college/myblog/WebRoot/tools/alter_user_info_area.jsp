<%@page import="com.yangkai.myblog.domain.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	User bloger=(User)session.getAttribute("bloger");
%>
<div style="border:#E97B56 solid 1px;" align="center">
<form action="AlterUserInfo" method="post" name="form1" id="form1">
  <table width="570" border="0">
    <tr>
      <td width="150" class="user_about"><a>基本信息</a></td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>真实姓名：</td>
      <td><input type="text" name="name" id="text_border" value="<%=bloger.getName()%>"/></td>
    </tr>
    <tr>
      <td>性别：</td>
      <td><select name="sex" id="text_border">
      <option selected="selected" value="<%=bloger.getSex()%>"><%=bloger.getSex()%></option>
        <option value="男">男</option>
        <option value="女">女</option>
      </select></td>
    </tr>
    <tr>
      <td>出生日期：</td>
      <td><input type="text" name="birthday" id="text_border" value="<%=bloger.getBirthday()%>"/>
        *格式为：0000-00-00</td>
    </tr>
    <tr>
      <td>所在地：</td>
      <td><input type="text" name="address" id="text_border"  value="<%=bloger.getAddress()%>"/></td>
    </tr>
    <tr>
      <td>邮箱：</td>
      <td><input type="text" name="mail" id="text_border"  value="<%=bloger.getMail()%>"/></td>
    </tr>
    <tr>
      <td>兴趣与爱好：</td>
      <td><textarea name="interest" id="text_border" cols="50" rows="3"><%=bloger.getInterest()%></textarea></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><input type="submit" name="button" id="button" value="确定" />
      <input type="reset" name="button2" id="button2" value="取消" /></td>
    </tr>

  </table>
  </form>
</div>
