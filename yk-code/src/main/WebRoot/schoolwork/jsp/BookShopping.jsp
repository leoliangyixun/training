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
      <td width="104">�û�����</td>
      <td width="204"><input name="user" type="text"/></td>
    </tr>
    <tr>
      <td>�� &nbsp;&nbsp;&nbsp;�룺</td>
      <td><input name="password" type="password"/></td>
    </tr>
    <tr>
      <td>��ѡ��ͼ�飺</td>
      <td>
      
      <select name="book" id="select">
        <option value="" selected="selected">��ѡ��</option>
        <option value="��ѧӢ��" >��ѧӢ��</option>
        <option value="�ߵ���ѧ">�ߵ���ѧ</option>
        <option value="��ѧ����">��ѧ����</option>
        <option value="JSP������">JSP������</option>
        <option value="C/C++�������">C/C++�������</option>
        <option value="Flash�������">Flash�������</option>
      </select></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><input type="submit" name="button" id="button" value="����" />
      <input type="reset" name="button2" id="button2" value="ȡ��" /></td>
    </tr>
  </table>
</form>
  </body>
</html>
