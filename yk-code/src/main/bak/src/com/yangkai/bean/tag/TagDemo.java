package com.yangkai.bean.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class TagDemo extends TagSupport {
	//JspWriter out=this.pageContext.getOut();
	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request=(HttpServletRequest) this.pageContext.getRequest();
		String ip=request.getRemoteAddr();
		JspWriter out=this.pageContext.getOut();
		try {
			//out.println("This is the Start Tag<br>");
			out.println(ip);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return EVAL_BODY_INCLUDE;
		
		//return super.doEndTag();
	}

	@Override
	public int doEndTag() throws JspException {
		//return super.doStartTag();
		JspWriter out=this.pageContext.getOut();
		try {
			out.println("<table border='1'>");
			out.println("<tr><td>fuck</td><td>fuck</td><td>fuck</td></tr>");
			out.println("<tr><td>fuck</td><td>fuck</td><td>fuck</td></tr>");
			out.println("<tr><td>fuck</td><td>fuck</td><td>fuck</td></tr>");
			out.println("</table>");
			//out.println("This is the End Tag");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return EVAL_PAGE;
	}	
}
