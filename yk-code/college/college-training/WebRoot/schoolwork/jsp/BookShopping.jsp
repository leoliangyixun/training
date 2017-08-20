<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>My JSP 'BookShopping.jsp' starting page</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
    <form id="form1"  method="post" action="BookResult.jsp" >
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
      
      <select name="book" id="select">
        <option value="" selected="selected">请选择</option>
        <option value="大学英语" >大学英语</option>
        <option value="高等数学">高等数学</option>
        <option value="大学物理">大学物理</option>
        <option value="JSP网络编程">JSP网络编程</option>
        <option value="C/C++程序设计">C/C++程序设计</option>
        <option value="Flash动画设计">Flash动画设计</option>
      </select></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><input type="submit" name="button" id="button" value="购买" />
      <input type="reset" name="button2" id="button2" value="取消" /></td>
    </tr>
  </table>
</form>
  </body>
</html>
