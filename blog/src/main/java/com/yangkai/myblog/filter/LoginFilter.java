package com.yangkai.myblog.filter;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class LoginFilter implements Filter {

    public LoginFilter() {
        
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		//PrintWriter out=response.getWriter();
		//System.out.println("before LoginFilter");
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		HttpSession session=req.getSession();
		String loginuser=(String) session.getAttribute("loginuser");
		/*
		 * ≤‚ ‘¥˙¬Î
		 */
		/*
		System.out.println(req.getRequestURI());
		chain.doFilter(request, response);
		System.out.println(req.getRequestURL().toString());
		*/
		//System.out.println(req.getRequestURI());
		//System.out.println(req.getRequestURI().endsWith("index.jsp"));
		//chain.doFilter(request, response);
		//System.out.println("after LoginFilter");
		if(loginuser!=null)
		{
			chain.doFilter(request, response);
		}else{
			if( req.getRequestURI().endsWith("index.jsp") || 
				req.getRequestURI().endsWith("regist.jsp") || 
				req.getRequestURI().endsWith("login.jsp")|| 
				req.getRequestURI().contains("blog_view.jsp") || 
				req.getRequestURI().endsWith("regist_success.jsp") ||
				req.getRequestURI().endsWith("message_add.jsp") || 
				req.getRequestURI().endsWith("error.jsp") || 
				req.getRequestURI().endsWith("friend_add.jsp"))
			{
				chain.doFilter(request, response);
			}else{
				resp.sendRedirect("index.jsp");
			}
			
		}
	}
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
