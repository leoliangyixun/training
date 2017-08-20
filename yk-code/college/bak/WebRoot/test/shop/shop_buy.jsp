<%@page import="com.yangkai.bean.shop.SimpleShopBean"%>
<%@page import="com.yangkai.bean.shop.SimpleShopDAO"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>购物首页</title>
    <style type="text/css">
    a{ text-decoration:none;}
    body{background-color:#E6EA95;}
    </style>
  </head>
  
  <body>
  <%
	request.setCharacterEncoding("gb2312");
	String name=request.getParameter("name");
	String good_num=request.getParameter("num");
  %>
 <div align="center">
  <form method="post" action="shop_buy.jsp">
  <table>
      <tr align="center">
        <td colspan="5"><a style="font-size:20px">简易购物车</a></td>
      </tr>
      <tr align="center">
        <td>商品列表：</td>
        <td>
        <select name="name">
          <option selected="selected" value="Default">==请选择商品==</option>
          <%---------------------------------将商品显示在下拉列表中---------------------------%>
          <%
          SimpleShopDAO dao_name=new SimpleShopDAO();
      	  Vector v_name=dao_name.getShopGoodsname();
          for(int i=0;i<v_name.size();i++)
          {
           		if(v_name.get(i)!=null)
           		{	
           			SimpleShopBean ssb=(SimpleShopBean)v_name.get(i);
           			out.println("<option value="+ssb.getName()+">"+ssb.getName()+"</option>");
  				}  
          } 
          %>  
        </select>
        </td>
        <td>购买数量：</td>
        <td><input name="num" type="text" size="10"  /></td>
        <td><input type="submit" value="添加到购物车" /></td>
      </tr>
    </table>
  </form>
</div>
<%-----------------------------------------添加商品到购物车------------------------------------%>
<%
if(name!=null && !name.equals("Default"))
{
	if(good_num!=null && !good_num.equals(""))
	{ 
	    int num=Integer.parseInt(good_num);
	    SimpleShopDAO dao_add=new SimpleShopDAO();
	    dao_add.addShopGoods(name,num);
	} 
}
%>
<div align="center">
  <table border="1" bordercolor="#000000" width="494" cellpadding="2" cellspacing="0" style="border-collapse:collapse;font-size:12px" >
      <tr align="center">
        <td colspan="6">以下是您购物车的商品</td>
      </tr>
      <tr align="center">
        <td>商品名称</td>
        <td>购买数量</td>
        <td>商品单价(元)</td>
        <td>商品总额(元)</td>
        <td>修改数量</td>
        <td>删除商品</td>
      </tr>
      <%------------------------------------显示购物车中的商品---------------------------------%>
      <%
    	  SimpleShopDAO dao_show=new SimpleShopDAO();
    	  Vector v_goods=dao_show.showShopGoods();
    	  if(v_goods==null)
    	  {
    		  out.println("<tr align='center'><td colspan='6'>您的购物车为空</td></tr>");
    	  }
    	  else{
		    	  double money=0.0;
		    	  for(int i=0;i<v_goods.size();i++)
		    	  {
		    		 // if(v_goods.get(i)!=null)
		    		 // {
		    			  SimpleShopBean ssb=(SimpleShopBean)v_goods.get(i);
		    			  out.println("<tr align='center'><td>"+ssb.getName()+"</td><td>"+ssb.getNum()+
		    					  "</td><td>"+ssb.getPrice()+"</td><td>"+ssb.getTotal()+
		    					  "</td><td><a href='shop_updatenum.jsp?name="+ssb.getName()+"&num="+ssb.getNum()+"'>修改</a></td><td><a href='DeleteShopGoodsServlet?name="+ssb.getName()+"'>删除</a></td></tr>");
		    			  money+=ssb.getTotal();
		    		 // }
		    	  }
		    	  out.println("<tr align='right'><td colspan='6'>您需付款"+
		    	              "<a style='color:#F00;font-size:24px;font-style:italic'>"+money+"</a>元</td></tr>");		  
		    	  }
     
      %>
    </table>
    </div>
  </body>
</html>
