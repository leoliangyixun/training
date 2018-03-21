<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<html>
  <head>
  </head>
  
  <body>
  <form  method="post" action="BookResult.jsp">
  <table width="280">
    <tr>
       <td width="111">请选择图书： </td>
      <td width="140">
         <label>
           <input type="radio" name="book" value="大学英语"  />
           大学英语</label>
         <br />
         <label>
          <input type="radio" name="book" value="高等数学" />
           高等数学</label>
         <br />
         <label>
           <input type="radio" name="book" value="C语言程序设计" />
           C语言程序设计</label>
         <br />
         <label>
           <input type="radio" name="book" value="Java程序设计" />
          Java程序设计</label>
         <br /></td>
    </tr>
    <tr>
      <td></td>
    </tr>
	<tr>
      <td></td>
	  <td><input type="submit" name="button" id="button" value="提交" />
      <input type="reset" name="button2" id="button2" value="重置" /></td>
    </tr>
  </table>
</form>
  </body>
</html>
