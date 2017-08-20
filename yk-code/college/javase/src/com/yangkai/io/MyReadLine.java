package com.yangkai.io;
import java.io.IOException;
import java.io.Reader;
public class MyReadLine {
	Reader r=null;
	public MyReadLine(Reader r) {
		this.r=r;
	}
	
	public  String myReadLine()
	{
		String str=null;
		StringBuffer sb=new StringBuffer();
		int num;
		try {
				while((num=r.read())!=-1)
				{
					if(num!='\r' && num!='\n')
					{
						sb.append((char)num);
						continue;
					}
					str=sb.toString();
				}	
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return str;
	}
	
}