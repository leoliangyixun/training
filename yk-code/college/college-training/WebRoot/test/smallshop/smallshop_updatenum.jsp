<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<jsp:useBean id="dao" class="com.yangkai.bean.shop.SimpleShopDAO" scope="session"></jsp:useBean>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'shop_updatenum.jsp' starting page</title>
    <style type="text/css">
    a{ text-decoration:none;}
    body{background-color:#E6EA95;}
    </style>
  </head>
  
  <body>
   <%
   String name=request.getParameter("name");
   if(name==null)
   {
	   out.println("<div align='center'>非法操作</div>");
   }
   else
   {
	   name=dao.setCharacterEncoding(request.getParameter("name"));
	   int num=Integer.parseInt(request.getParameter("num"));
  %>
   <div align="center">
   <form action="UpdateShopGoodsServlet" method="get">
   <table border="1" bordercolor="#000000" width="494" cellpadding="4" cellspacing="0" style="border-collapse:collapse;font-size:12px">
   <tr align="center" style="font-size:20px">
   <td colspan="4">修改购物车</td>
   </tr>
   <tr align="center">
   <td>商品名称</td><td>商品当前数量</td><td>数量</td>
   </tr>
   <tr align="center">
   <td><%=name%><input type="hidden" name="name" value="<%=name%>"></td>
   <td><%=num%></td>
   <td><input name="num" type="text" size="10"/></td>
   <td><input type="submit" value="确定"><input type="reset" value="取消"></td>
   </tr>
   <tr align="center" style="font-size:20px;font-style:italic">
   <td colspan="4"><a href="javascript:window.history.back()">返回</a></td>
   <!------------------------------相当于浏览器的后退功能------------------------------>
   <!--td colspan="4"><a href="shop_buy.jsp">返回</a></td-->
   <!------------------------------重新加载shop_buy.jsp页面--------------------------->
   </tr>
   </table>
   </form>
   </div>
   <%}%>
  </body>
</html>
