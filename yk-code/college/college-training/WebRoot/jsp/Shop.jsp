<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<html>
  <head>     
  </head>
  
  <body>
    <form id="form1"  method="post" action="ShopResult.jsp" >
  <table width="320" height="100"  >
    <tr>
      <td width="104">用户名：</td>
      <td width="204"><input name="user" type="text"/></td>
    </tr>
    <tr>
      <td>密 &nbsp;&nbsp;&nbsp;码：</td>
      <td><input name="password" type="password"/></td>
    </tr>
    <tr>
      <td>请选择图书：</td>
      <td>
      <select name="mybook" id="select">
        <option value="大学英语" >大学英语</option>
        <option value="高等数学">高等数学</option>
        <option value="JavaWeb开发">JavaWeb开发</option>
        <option value="C#程序设计">C#程序设计</option>
      </select></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><input type="submit" name="button" id="button" value="确定" />
      <input type="reset" name="button2" id="button2" value="取消" /></td>
    </tr>
  </table>
</form>
  </body>
</html>
