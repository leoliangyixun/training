package com.yangkai.servlet.shop;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yangkai.bean.shop.SimpleShopDAO;
public class DeleteShopGoodsServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SimpleShopDAO dao=new SimpleShopDAO();
		String name=dao.setCharacterEncoding(request.getParameter("name"));
        /*	 
		request.setCharacterEncoding("gb2312");//������ַ����û�����ã��мǡ�
		String name=request.getParameter("name");
        */
		dao.deleteShopGoods(name);
		response.sendRedirect("shop_buy.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doGet(request, response);
	}
}
