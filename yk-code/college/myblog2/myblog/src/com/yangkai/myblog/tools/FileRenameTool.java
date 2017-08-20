package com.yangkai.myblog.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FileRenameTool {

	public Date datetime=null;
	public int count=3;
	public FileRenameTool(Date datetime)
	{
		this.datetime=datetime;
	}
	public String getFileName(String ip)
	{
		StringBuffer buf=new StringBuffer();
		String[] s=ip.split("\\.");
		for(int i=0;i<s.length;i++)
		{
			buf.append(this.addZero(s[i],s[i].length()));
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		buf.append(sdf.format(datetime));
		for(int i=0;i<this.count;i++)
		{
			buf.append(new Random().nextInt(10));
		}
		String str=buf.toString();
		return str;
	}
	public String addZero(String str,int len)
	{
		StringBuffer buf=new StringBuffer(str);
		if(buf.length()<3)
		{
			for(int i=0;i<3-len;i++)
			{
				buf.insert(0,"0");
			}
		}
		str=buf.toString();
		return str;
	}

}
