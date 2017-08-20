package com.yangkai.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class HelloServlet extends HttpServlet 
{
	@Override
	public void init(ServletConfig config) throws ServletException 
	{super.init(config);}
	@Override
	public void service(ServletRequest req, ServletResponse resp)
			throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		//super.service(req, resp);
		resp.setContentType("text/html ;charset=utf-8");
		PrintWriter out=resp.getWriter();
		out.println("<html><body>");
		out.println("�����ҵĵ�һ��Servlet����.");
		out.println("</body></html>");	
	}
	public void destroy(){}
}
