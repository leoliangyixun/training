package com.yangkai.servlet.filter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class EncodingFilter implements Filter {
	protected String encoded=null;
	protected boolean enabled=false;
	
	public void init(FilterConfig filterConfig) throws ServletException
	{
		/*
		encoded=filterConfig.getInitParameter("encoded");
		String value=filterConfig.getInitParameter("enabled");
		if(value.equalsIgnoreCase("true"))
			enabled=true;
		else
			enabled=false;
		*/
	}
	
	public void destroy()
	{
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
			
		    System.out.println("EncodingFilter invoked!!!");
			request.setCharacterEncoding("GBK");
		    String username=request.getParameter("username");
		    System.out.println(username);
			chain.doFilter(request, response);
			
	}
}
