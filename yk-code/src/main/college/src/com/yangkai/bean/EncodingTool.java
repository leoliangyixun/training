package com.yangkai.bean;

import java.io.UnsupportedEncodingException;

public class EncodingTool {
	public String setCharacterEncoding(String str)
			throws UnsupportedEncodingException {
		return new String(str.getBytes("ISO-8859-1"), "GBK");
	}
}
