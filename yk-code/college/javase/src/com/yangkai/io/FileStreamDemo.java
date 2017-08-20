package com.yangkai.io;
import java.io.BufferedReader;
import java.io.BufferedWriter;
//import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class FileStreamDemo {
	public static void main(String[] args) {	
	try {
		//File f=new File("1.txt");
		FileReader fr=new FileReader("1.txt");
		BufferedReader br=new BufferedReader(fr);
		char[] buff=new char[4096];
		try {
			 //len=br.read(buff, 0, (int)f.length());
			int len=br.read(buff);
			br.close();
			FileWriter fw = new FileWriter("4.txt");
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(buff,0,len);
			//System.out.println(new String(buff,0,len));
			bw.close();
			
			FileReader fr1=new FileReader("4.txt");
			BufferedReader br1=new BufferedReader(fr1);
			String strLine;
			while((strLine=br1.readLine())!=null)
			{
				System.out.println(strLine);
			}
			br1.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	} catch (FileNotFoundException e) {
		System.out.println("找不到文件");
	}
	

	}

}
