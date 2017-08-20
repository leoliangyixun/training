package com.yangkai.io;
import java.io.*;
public class Stream{
	public static void main(String[] args) throws Exception
	{
		
//	    int data;
//		while((data=System.in.read())!=-1)
//		{
//			System.out.write(data);
//			//System.out.println(data);
//		}
		
//		int data;
//		while(true)
//		{
//			if((data=System.in.read())!=-1)
//			{
//				System.out.write(data);
//			}else{
//				System.out.println("已经读到流的末尾！！！");
//			}
//		}
		
		int data;
		
		/*
		int data;
		if((data=System.in.read())!=-1)
		{
			System.out.write(data);
		
		if((data=System.in.read())!=-1)
		{
			System.out.write(data);
			System.out.flush();
			//System.out.println(data);
		}
		
		/*
		int data;
		data=System.in.read();
	    PrintStream out=System.out;
		out.write(data);
		out.flush();
		//out.println(data);
		*/
		
/*	
 	    System.out.println((char)('\n'+'0'));//------>:
		System.out.println('\n'+'0');//------>58
		System.out.println('\n'+0);//------>10
*/		
		
//		int data;
//		while((data=System.in.read())!=-1)
//		{
//			System.out.write(data);
//		}
		
//		while(true)
//		{
//			int data=System.in.read();
//			System.out.write(data);
//		}
		
//		while(true)
//		{
//			int data=System.in.read();
//			System.out.print((char)data);
//		}
		
		/**
		 * 需求：按回车键输出输入信息，输入over，则退出输入。
		 * */
//		StringBuilder sb=new StringBuilder();
//		while(true)
//		{
//			int data=System.in.read();
//			if(data!='\r' && data!='\n')
//			{
//				sb.append((char)data);
//				if(sb.toString().equals("over"))
//				{
//					break;
//				}
//				continue;
//			}else{
//				if(data=='\r')
//				{
//					System.out.println(sb.toString());	
//					sb.delete(0, sb.length());
//				}
//			}
//		}
		
//		StringBuilder sb=new StringBuilder();
//		while(true)
//		{
//			int data=System.in.read();
//			if((char)data!='\r')
//			{
//				sb.append((char)data);
//			}else{
//				break; 
//			}
//		}
//		System.out.println(sb.length());
//		System.out.println(sb.toString());
		
//		while(true)
//		{
//			int data=System.in.read();
//			System.out.println(data);
//		}
		
//		int data;
//		int i=1;
//		while((data=System.in.read())!=-1)
//		{
//			System.out.write(data);
//			System.out.println(i);
//			i++;
//		}
		
		/**
		 * 需求：按回车键输出输入信息，输入over，则退出输入。
		 * */
//		StringBuilder sb=new StringBuilder();
//		while(true)
//		{
//			int data=System.in.read();
//			if(data!='\r' && data!='\n')
//			{
//				sb.append((char)data);
//				if(sb.toString().equals("over"))
//				{
//					break;
//				}
//				continue;
//			}
//			System.out.println(sb.toString());	
//			//System.out.println(sb==null);
//			//System.out.println(sb.length());	
//			//sb.delete(0, sb.length());
//		}
	
	}
}
