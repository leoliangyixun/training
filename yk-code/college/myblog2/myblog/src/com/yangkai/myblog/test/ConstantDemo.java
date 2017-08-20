package com.yangkai.myblog.test;

import com.yangkai.myblog.constants.Constants;

public class ConstantDemo {

	public static void main(String[] args) 
	{
		String sql = "SELECT * FROM blog WHERE blog_level="+Constants.BLOG_STATE_FOR_RELEASE+" ORDER BY blog_date DESC LIMIT 0,20";
		System.out.println(sql);
	}

}
