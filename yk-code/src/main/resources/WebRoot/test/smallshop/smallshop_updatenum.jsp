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
	   out.println("<div align='center'>�Ƿ�����</div>");
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
   <td colspan="4">�޸Ĺ��ﳵ</td>
   </tr>
   <tr align="center">
   <td>��Ʒ����</td><td>��Ʒ��ǰ����</td><td>����</td>
   </tr>
   <tr align="center">
   <td><%=name%><input type="hidden" name="name" value="<%=name%>"></td>
   <td><%=num%></td>
   <td><input name="num" type="text" size="10"/></td>
   <td><input type="submit" value="ȷ��"><input type="reset" value="ȡ��"></td>
   </tr>
   <tr align="center" style="font-size:20px;font-style:italic">
   <td colspan="4"><a href="javascript:window.history.back()">����</a></td>
   <!------------------------------�൱��������ĺ��˹���------------------------------>
   <!--td colspan="4"><a href="shop_buy.jsp">����</a></td-->
   <!------------------------------���¼���shop_buy.jspҳ��--------------------------->
   </tr>
   </table>
   </form>
   </div>
   <%}%>
  </body>
</html>
