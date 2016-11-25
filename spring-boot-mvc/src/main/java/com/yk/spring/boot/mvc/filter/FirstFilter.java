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
public class FirstFilter implements Filter {


    public FirstFilter() {
    
    }


	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("enter first filter )");
	    long start = System.currentTimeMillis();
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String path = httpServletRequest.getRequestURI();
        String queryStr = httpServletRequest.getQueryString();
        if (queryStr != null) {
            path += "?" + httpServletRequest.getQueryString();
        }
		chain.doFilter(httpServletRequest, httpServletResponse);

		log.info("access url [{}], cost time [{}] ms )", path, System.currentTimeMillis() - start);
        log.info("out first filter )");
	}


	public void init(FilterConfig fConfig) throws ServletException {
	
	}

}
