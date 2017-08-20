package com.yangkai.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestDemo {

	public static int fun(int m)
	{
		int count=0;
		String s=String.valueOf(m);
		int len=s.length();
		for(int i=0;i<len;i++)
		{
			char ch=s.charAt(i);
			if(ch=='1')
			{
				count++;
			}
		}
		return count;
	}
	public static  void main(String[] args) 
	{
	
		char[] buff=new char[1024];
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int len=0;
		try {
			while((len=br.read(buff))!=-1)
			{
				String s=new String(buff,0,len-2);//要去掉回车符"\r\n"
				int m=Integer.parseInt(s);
//			    System.out.println();
				int count=fun(m);
				System.out.println(count);
			}
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	
	
	/*
		String s="112134121";
		int m=Integer.parseInt(s);
		int count=fun(m);
		//System.out.println(m);
		System.out.println(count);
	*/
	}

}
