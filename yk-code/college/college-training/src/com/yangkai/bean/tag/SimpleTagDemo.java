package com.yangkai.bean.tag;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SimpleTagDemo extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		JspFragment jf=this.getJspBody();
		StringWriter sw=new StringWriter();
		jf.invoke(sw);
		String str=sw.toString();
		str=str.toUpperCase();	
		//jf.invoke(this.getJspContext().getOut());
		this.getJspContext().getOut().write(str);
		this.getJspContext().getOut().println(str);
	}

	
}
