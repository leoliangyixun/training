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
      <td width="111" height="24">�û�����</td>
      <td width="197"><input name="user" type="text" id="textfield"  /></td>
    </tr>
    <tr>
      <td height="21">�� &nbsp;&nbsp;&nbsp;�룺</td>
      <td><input name="password" type="password" id="textfield2" /></td>
    </tr>
    <tr>
      <td height="47">��ѡ��ͼ�飺</td>
      <td><label>
        <input type="checkbox" name="book" value="��ѧӢ��" id="book_0" />
        ��ѧӢ��</label>
        <br />
        <label>
          <input type="checkbox" name="book" value="��ѧ����" id="book_1" />
          ��ѧ����</label>
        <br />
        <label>
          <input type="checkbox" name="book" value="�������" id="book_2" />
          �������</label>
        <br />
        <label>
          <input type="checkbox" name="book" value="�ߵ���ѧ" id="book_3" />
          �ߵ���ѧ</label>
        <br />
        <label>
          <input type="checkbox" name="book" value="Java�������" id="book_4" />
          Java�������</label>
        <br />
        <label>
          <input type="checkbox" name="book" value="������" id="book_5" />
      ������</label></td>
    </tr>
    <tr>
      <td height="23">&nbsp;</td>
      <td><input type="submit" name="button" id="button" value="����" />
      <input type="reset" name="button2" id="button2" value="ȡ��" /></td>
    </tr>
  </table>
</form>
  </body>
</html>
