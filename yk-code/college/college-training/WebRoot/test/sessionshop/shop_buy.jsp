<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>shop.html</title>
    <meta http-equiv="content-type" content="text/html; charset=gb2312">

  </head>
  
  <body>
 <div align="center">
<form action="shop_check.jsp" method="post">
<table  border="0">
  <tr>
    <td colspan="2" align="center">session简易购物车</td>
  </tr>
  <tr>
    <td>商品：</td>
    <td>
     <input type="text" name="goods"  /></td>
  </tr>
  <tr>
    <td colspan="2" align="center">
    <input type="submit"  value="购买" />
     <input type="reset"  value="取消" /></td>
    </tr>
</table>
</form>
</div>
  </body>
</html>
