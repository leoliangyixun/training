package com.yangkai.servlet.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.yangkai.bean.EncodingTool;

public class MessageFilter implements Filter {
	public MessageFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		EncodingTool sc = new EncodingTool();
		String username = sc.setCharacterEncoding(((HttpServletRequest) request).getParameter("username"));
		String content = sc.setCharacterEncoding(((HttpServletRequest) request).getParameter("content"));
		content = content.replace("������", "������");
		content = content.replace("SB", "**");
		content = content.replace("sb", "**");
		content = content.replace("��", "fuck");
		content = content.replace("����", "**");
		content = content.replace("ɵ��", "**");
		request.setAttribute("username", username);
		request.setAttribute("content", content);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}
}
