<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>My JSP 'BooksShopping.jsp' starting page</title> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  <form id="form1"  method="post" action="BooksResult.jsp" >
  <table width="304" height="127"  >
    <tr>
      <td width="111" height="24">用户名：</td>
      <td width="197"><input name="user" type="text" id="textfield"  /></td>
    </tr>
    <tr>
      <td height="21">密 &nbsp;&nbsp;&nbsp;码：</td>
      <td><input name="password" type="password" id="textfield2" /></td>
    </tr>
    <tr>
      <td height="47">请选择图书：</td>
      <td><label>
        <input type="checkbox" name="book" value="大学英语" id="book_0" />
        大学英语</label>
        <br />
        <label>
          <input type="checkbox" name="book" value="大学物理" id="book_1" />
          大学物理</label>
        <br />
        <label>
          <input type="checkbox" name="book" value="动画设计" id="book_2" />
          动画设计</label>
        <br />
        <label>
          <input type="checkbox" name="book" value="高等数学" id="book_3" />
          高等数学</label>
        <br />
        <label>
          <input type="checkbox" name="book" value="Java程序设计" id="book_4" />
          Java程序设计</label>
        <br />
        <label>
          <input type="checkbox" name="book" value="网络编程" id="book_5" />
      网络编程</label></td>
    </tr>
    <tr>
      <td height="23">&nbsp;</td>
      <td><input type="submit" name="button" id="button" value="购买" />
      <input type="reset" name="button2" id="button2" value="取消" /></td>
    </tr>
  </table>
</form>
  </body>
</html>
