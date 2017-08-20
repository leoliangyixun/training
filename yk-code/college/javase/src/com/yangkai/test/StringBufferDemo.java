package com.yangkai.test;

public class StringBufferDemo {

	public static void main(String[] args) {
	StringBuffer sb=new StringBuffer("Hello");
	StringBuffer sb1=new StringBuffer();
	//char[] c={'W','o','r','l','d'};
	String ip="192.168.16.1";
	//String[] name={"yangkai","fucui"};
	String[] sip=ip.split("\\.");
	for(int i=0;i<sip.length;i++)
	{
		sb1.append(sip[i]);
	}
	System.out.println(sb1);
	//s.insert(5, c);
	//s.reverse();//×Ö·û·´×ª¡£
	//String s="World";
	//sb.append(s);
	//System.out.println(sb);
	//System.out.println(name[1]);
	}

}
