package com.yangkai.servlet.shop;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yangkai.bean.shop.SimpleShopDAO;
public class UpdateShopGoodsServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		if(request.getParameter("num").equals(""))
		{
			out.println("<script language='javascript'>alert('You must be kidding!!!');history.back();</script>");
		}
		else{
			SimpleShopDAO dao=new SimpleShopDAO();
            //name��shop_buy.jspҳ�����Ѿ������ˣ������ظ����롣��ͬһ��WebӦ���У�ʹ��ͬһ�ַ�ʽ��õĲ���ֻ��Ҫ����һ��.
			//����Ҫע�����JVMʹ�õ��ַ���Web����ʹ�õ��ַ�һ����ˣ���JVM��Web�������ݲ������Web������JVM���ݲ���ʱһ��Ҫע���ַ�����ת����
			String name=dao.setCharacterEncoding(request.getParameter("name"));
			int num=Integer.parseInt(request.getParameter("num"));
			//out.println(name+","+num);//Ҫ��������д�ӡ��name����Ҫ���롣
			dao.updateShopGoodsnum(name,num);//��name��Ϊ����ݸ�JVM����Ҫ����
			response.sendRedirect("shop_buy.jsp");
		}	
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doGet(request, response);
	}

}
