package com.yangkai.io;
import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class MyBufferedReaderTest{
	public static void main(String[] args) 
	{ 
		try {
			FileReader fr=new FileReader("Stream.java");
			InputStreamReader in=new InputStreamReader(System.in);
			//MyBufferedReader myBufferedReader=new MyBufferedReader(in);
			/*
			while((str=myBufferedReader.myReadLine())!=null)
			{
				System.out.println(str);
			}
			*/
			
			/*
			while(true)
			{
				String str=myBufferedReader.myReadLine();
				System.out.println(str);
				if(str==null)
				{
					System.out.println("end of stream!!!");
				}
			}
			*/
			
			/*
			String str=null;
			while((str=myBufferedReader.myReadLine())!=null)
			{
					System.out.println(str);
			}
			*/
			
			MyBufferedReader myBufferedReader=new MyBufferedReader(fr);
			String str=null;
			while((str=myBufferedReader.myReadLine())!=null)
			{
					System.out.println(str);
			}
			fr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
class MyBufferedReader{
	Reader r=null;
	public MyBufferedReader(Reader r) 
	{
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
					if(num=='\n')
					{
						return sb.toString();
					}
				}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		if(sb.length()>0)
		{
			return sb.toString();
		}else{
			System.out.println("end of stream!!!");
			return null;
		}
	}
}