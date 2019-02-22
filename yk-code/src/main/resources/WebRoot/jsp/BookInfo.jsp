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
       <td width="111">请选择图书： </td>
      <td width="140" rowspan="2">
         <label>
           <input type="radio" name="BookInfo" value="大学英语"  />
           大学英语</label>
         <br />
         <label>
          <input type="radio" name="BookInfo" value="高等数学" />
           高等数学</label>
         <br />
         <label>
           <input type="radio" name="BookInfo" value="C语言程序设计" />
           C语言程序设计</label>
         <br />
         <label>
           <input type="radio" name="BookInfo" value="Java程序设计" />
          Java程序设计</label>
         <br /></td>
    </tr>
    <tr>
      <td><input type="submit" name="button" id="button" value="提交" />
      <input type="reset" name="button2" id="button2" value="重置" /></td>
    </tr>
  </table>
</form>
  </body>
</html>
