<%@page import="com.yangkai.bean.shop.SimpleShopBean"%>
<%@page import="com.yangkai.bean.shop.SimpleShopDAO"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>������ҳ</title>
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
        <td colspan="5"><a style="font-size:20px">���׹��ﳵ</a></td>
      </tr>
      <tr align="center">
        <td>��Ʒ�б�</td>
        <td>
        <select name="name">
          <option selected="selected" value="Default">==��ѡ����Ʒ==</option>
          <%---------------------------------����Ʒ��ʾ�������б���---------------------------%>
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
        <td>����������</td>
        <td><input name="num" type="text" size="10"  /></td>
        <td><input type="submit" value="��ӵ����ﳵ" /></td>
      </tr>
    </table>
  </form>
</div>
<%-----------------------------------------�����Ʒ�����ﳵ------------------------------------%>
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
        <td colspan="6">�����������ﳵ����Ʒ</td>
      </tr>
      <tr align="center">
        <td>��Ʒ����</td>
        <td>��������</td>
        <td>��Ʒ����(Ԫ)</td>
        <td>��Ʒ�ܶ�(Ԫ)</td>
        <td>�޸�����</td>
        <td>ɾ����Ʒ</td>
      </tr>
      <%------------------------------------��ʾ���ﳵ�е���Ʒ---------------------------------%>
      <%
    	  SimpleShopDAO dao_show=new SimpleShopDAO();
    	  Vector v_goods=dao_show.showShopGoods();
    	  if(v_goods==null)
    	  {
    		  out.println("<tr align='center'><td colspan='6'>���Ĺ��ﳵΪ��</td></tr>");
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
		    					  "</td><td><a href='shop_updatenum.jsp?name="+ssb.getName()+"&num="+ssb.getNum()+"'>�޸�</a></td><td><a href='DeleteShopGoodsServlet?name="+ssb.getName()+"'>ɾ��</a></td></tr>");
		    			  money+=ssb.getTotal();
		    		 // }
		    	  }
		    	  out.println("<tr align='right'><td colspan='6'>���踶��"+
		    	              "<a style='color:#F00;font-size:24px;font-style:italic'>"+money+"</a>Ԫ</td></tr>");		  
		    	  }
     
      %>
    </table>
    </div>
  </body>
</html>
