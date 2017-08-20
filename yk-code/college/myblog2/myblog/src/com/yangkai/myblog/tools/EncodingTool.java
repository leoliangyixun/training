package com.yangkai.myblog.tools;

import java.io.UnsupportedEncodingException;

public class EncodingTool {
	public static final String setCharacterEncoding(String str)
			throws UnsupportedEncodingException 
	{
		return new String(str.getBytes("ISO-8859-1"), "GBK");
	}
}
