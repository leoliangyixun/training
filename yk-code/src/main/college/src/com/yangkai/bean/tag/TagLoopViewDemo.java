package com.yangkai.bean.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class TagLoopViewDemo extends TagSupport {
	int n=5;
	@Override
	public int doStartTag() throws JspException {
		
		return EVAL_BODY_INCLUDE;
	}
	@SuppressWarnings("unused")
	@Override
	public int doAfterBody() throws JspException {
		n--;
		if(n>0)
		{
			return EVAL_BODY_AGAIN;
		}
		else{
		return SKIP_BODY;
		}
	}
}
