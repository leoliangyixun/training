package com.yangkai.bean.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class TagViewDemo extends TagSupport {

	@Override
	public int doStartTag() throws JspException {
		//return SKIP_BODY;
		return EVAL_BODY_INCLUDE;
	}

}
