package com.yk.spring.boot.mvc.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class AuthFilter implements Filter {


    public AuthFilter() {
    
    }


	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("enter auth filter )");
	    long start = System.currentTimeMillis();
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        log.info("auth check, cost time [{}] ms )", System.currentTimeMillis() - start);
   
		chain.doFilter(httpServletRequest, httpServletResponse);
        log.info("out auth filter )");
	}


	public void init(FilterConfig fConfig) throws ServletException {
	
	}

}
