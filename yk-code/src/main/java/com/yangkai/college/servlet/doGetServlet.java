package com.yangkai.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class doGetServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		String user=req.getParameter("username");
		resp.setContentType("text/html;charset=GB2312");
		user=new String(user.getBytes("ISO-8859-1"));
		PrintWriter out=resp.getWriter();
		out.println("<html><body>");
		out.println("��ӭ"+user+"���ٱ���վ");
		out.println("</body></html>");
		
	}
	public void destroy(){}
}
