package com.yangkai.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileStreamCopy {

	public static void main(String[] args) {
		FileReader fr=null;
		FileWriter fw=null;
		try {
			fr = new FileReader("Stream.java");
			fw = new FileWriter("Stream.txt");
			int len;
			/*
			long start=System.currentTimeMillis();
			while((len=fr.read())!=-1)
			{
				fw.write(len);
			}
				long end=System.currentTimeMillis();
			*/
			
			char[] buff=new char[100];
			long start=System.currentTimeMillis();

			while((len=fr.read(buff))!=-1)
			{
				fw.write(buff,0,len);
				//fw.write(buff);
			}
			long end=System.currentTimeMillis();
			System.out.println("time:"+(end-start));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
	}
}
