package com.yangkai.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class StringBufferDemo2 {
 
	public String ip=null;
	public Date time=null;
	public int n;
	public StringBufferDemo2(String ip,Date time,int n){
		this.ip=ip;
		this.time=time;
		this.n=n;
	}
	public static void main(String[] args) {
		StringBuffer s=null;
		String ip="192.16.1.10";
		int n=6;
		StringBufferDemo2 buf=new StringBufferDemo2(ip,new Date(),n);
		s=buf.getStringBuffer();
		System.out.println("生成的字符串为："+s);
	}
	public StringBuffer getStringBuffer(){
		StringBuffer buf=new StringBuffer();
		String[] s=this.ip.split("\\.");
		for(int i=0;i<s.length;i++)
		{
			buf.append(this.addZero(s[i],s[i].length()));
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		buf.append(sdf.format(time));
		for(int i=0;i<this.n;i++)
		{
			buf.append(new Random().nextInt(10));
		}
		return buf;
	}
	public String addZero(String str,int n)
	{
		StringBuffer buf=new StringBuffer(str);
		if(buf.length()<3)
		{
			for(int i=0;i<3-n;i++)
			{
				buf.insert(0,"0");
			}
		}
		str=buf.toString();
		return str;
	}
}
