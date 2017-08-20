package com.yangkai.net.tcp;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
public class FileUploadDemo{
	public static void main(String[] args) {
		
	}
}
class FileUploadServer{
	public static void main(String[] args) throws IOException 
	{
		ServerSocket ss = new ServerSocket(60000);
		Socket s=ss.accept();
		BufferedReader br =new BufferedReader(new InputStreamReader(s.getInputStream()));
		PrintWriter filepw=new PrintWriter(new FileWriter("test_fileupload.txt"),true);
		String str=null;
		while((str=br.readLine())!=null)
		{
			filepw.println(str);
		}
		PrintWriter socketpw=new PrintWriter(s.getOutputStream(),true);
		socketpw.println("upload success!");
		System.out.println("upload success!");
		br.close();
		filepw.close();
		socketpw.close();
		s.close();
		ss.close();
		
	}
}
class FileUploadClient{
	public static void main(String[] args) throws UnknownHostException, IOException 
	{
		Socket s=new Socket("192.168.16.100",60000);
		BufferedReader br=new BufferedReader(new FileReader("test.txt"));
		PrintWriter socketpw=new PrintWriter(s.getOutputStream(),true);
		String str=null;
		while((str=br.readLine())!=null)
		{
			socketpw.println(str);
		}
		s.shutdownOutput();
		BufferedReader socketbr=new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		if((str=socketbr.readLine())!=null)
		{
			System.out.println("response from server:"+str);
		}
	   
		/*
	    str=socketbr.readLine();//this sentence failed!!! What is the reason?
	    System.out.println("response from server:"+str);
	    */
		br.close();
		socketpw.close();
		socketbr.close();
		s.close();
	}
}