<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'BookInfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  <form  method="post" action="BookInfoResult.jsp">
  <table width="280">
    <tr>
       <td width="111">��ѡ��ͼ�飺 </td>
      <td width="140" rowspan="2">
         <label>
           <input type="radio" name="BookInfo" value="��ѧӢ��"  />
           ��ѧӢ��</label>
         <br />
         <label>
          <input type="radio" name="BookInfo" value="�ߵ���ѧ" />
           �ߵ���ѧ</label>
         <br />
         <label>
           <input type="radio" name="BookInfo" value="C���Գ������" />
           C���Գ������</label>
         <br />
         <label>
           <input type="radio" name="BookInfo" value="Java�������" />
          Java�������</label>
         <br /></td>
    </tr>
    <tr>
      <td><input type="submit" name="button" id="button" value="�ύ" />
      <input type="reset" name="button2" id="button2" value="����" /></td>
    </tr>
  </table>
</form>
  </body>
</html>
