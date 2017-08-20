package com.yangkai.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderDemo {

	public static void main(String[] args) {
	try{
		FileReader fr=new FileReader("4.txt");
		BufferedReader br=new BufferedReader(fr);
		String strLine;
		while((strLine=br.readLine())!=null)
		{
			System.out.println(strLine);
		}
		br.close();
		
	} catch (IOException e) {
		e.printStackTrace();
	}	
	}

}
