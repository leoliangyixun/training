<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<html>
  <head>
  </head>
  
  <body>
  <form  method="post" action="BookResult.jsp">
  <table width="280">
    <tr>
       <td width="111">��ѡ��ͼ�飺 </td>
      <td width="140">
         <label>
           <input type="radio" name="book" value="��ѧӢ��"  />
           ��ѧӢ��</label>
         <br />
         <label>
          <input type="radio" name="book" value="�ߵ���ѧ" />
           �ߵ���ѧ</label>
         <br />
         <label>
           <input type="radio" name="book" value="C���Գ������" />
           C���Գ������</label>
         <br />
         <label>
           <input type="radio" name="book" value="Java�������" />
          Java�������</label>
         <br /></td>
    </tr>
    <tr>
      <td></td>
    </tr>
	<tr>
      <td></td>
	  <td><input type="submit" name="button" id="button" value="�ύ" />
      <input type="reset" name="button2" id="button2" value="����" /></td>
    </tr>
  </table>
</form>
  </body>
</html>
