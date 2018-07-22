package com.yangkai.servlet.filter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.bean.EncodingTool;

public class LoginCheckFilter implements Filter {

	public LoginCheckFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		// request.setCharacterEncoding("gb2312");
		String username = request.getParameter("username");
		/*
		 * if(username==null) {
		 * ((HttpServletResponse)response).sendRedirect("login.html"); } else{
		 * //out.println("����Filter�е�username:"+username+"<br>");
		 * SetCharacterEncoding sc=new SetCharacterEncoding();
		 * username=sc.getCharacterEncoding(request.getParameter("username"));
		 * ((HttpServletRequest)request).setAttribute("username", username);
		 * //out.println("���ǹ�����������׶�"); chain.doFilter(request, response);
		 * //out.println("���ǹ���������Ӧ�׶�"); //out.close(); }
		 */
		HttpSession session = ((HttpServletRequest) request).getSession();
		if (session.getAttribute("username") == null) {
			// ((HttpServletResponse)response).sendRedirect("login.html");
			// out.println(((HttpServletRequest)
			// request).getRequestURI());/*/jsp/filter/LoginCheckServlet*/
			// out.println(((HttpServletRequest)
			// request).getRequestURL());/*http://localhost:8080/jsp/filter/LoginCheckServlet
			// */
			// return;
		} else {
			EncodingTool sc = new EncodingTool();
			username = sc.setCharacterEncoding(request.getParameter("username"));
			((HttpServletRequest) request).setAttribute("username", username);
			chain.doFilter(request, response);
		}
		// System.out.println("fuck");
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
