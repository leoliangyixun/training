<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>My JSP 'shop_check.jsp' starting page</title>
  </head>
  
  <body>
  <%
  request.setCharacterEncoding("gb2312");
  
  String goods=request.getParameter("goods");
  if(goods==null||goods.equals(""))
  {
	  response.sendRedirect("shop_buy.jsp");
	  out.close();
  }
  else
  {
  	  if((ArrayList)session.getAttribute("mylist")==null)
  	  {
  		   ArrayList mylist=new ArrayList();
  	   	   mylist.add(goods);
  	   	   session.setAttribute("mylist", mylist);
  	  }
  	  else
  	  {
  		  ArrayList mylist=(ArrayList)session.getAttribute("mylist");
  		  mylist.add(goods);
  	  }  
  }
  %>
  <div align="center">
       ������ʾ�� ��ղŹ�����<%=goods%><br>
  <input type="button" name="buy" value="���ؼ�������" onclick="javascript:location.href='shop_buy.jsp'"/>
  <input type="button" name="pay" value="����" onclick="javascript:location.href='shop_pay.jsp'"/>
 </div>
  </body>
</html>
