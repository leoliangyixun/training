package com.yangkai.myblog.tools;

import java.io.UnsupportedEncodingException;

public class EncoderUtil {
	public static final String encode(String str)throws UnsupportedEncodingException 
	{
		return new String(str.getBytes("ISO-8859-1"), "UTF-8");
	}
}
