<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<html>
  <head>     
  </head>
  
  <body>
    <form id="form1"  method="post" action="ShopResult.jsp" >
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
      <select name="mybook" id="select">
        <option value="��ѧӢ��" >��ѧӢ��</option>
        <option value="�ߵ���ѧ">�ߵ���ѧ</option>
        <option value="JavaWeb����">JavaWeb����</option>
        <option value="C#�������">C#�������</option>
      </select></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><input type="submit" name="button" id="button" value="ȷ��" />
      <input type="reset" name="button2" id="button2" value="ȡ��" /></td>
    </tr>
  </table>
</form>
  </body>
</html>
