package com.yangkai.io;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class EncodeDemo {

	public static void main(String[] args) throws UnsupportedEncodingException 
	{
		String name="Ñî¿ª";
		
		byte[] b1 = name.getBytes("UTF-8");
		System.out.println(Arrays.toString(b1)+"--->UTF-8");
		byte[] b2= name.getBytes("GBK");
		System.out.println(Arrays.toString(b2)+"--->GBK");
		String name1=new String(b2,"UTF-8");
		System.out.println(name1);
		byte[] b3=name1.getBytes("UTF-8");
		System.out.println(new String(b3,"GBK"));
		
		

	}

}
