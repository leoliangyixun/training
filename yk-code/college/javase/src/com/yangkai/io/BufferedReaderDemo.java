package com.yangkai.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedReaderDemo {

	public static void main(String[] args) {
		FileReader fr=null;
		FileWriter fw=null;
		BufferedReader br=null;
		BufferedWriter bw=null;
		try {
			 fr=new FileReader("Stream.java");
			 fw=new FileWriter("Stream.txt");
			 br=new BufferedReader(fr);
			 bw=new BufferedWriter(fw);
			String str=null;
			while((str=br.readLine())!=null)
			{
				//System.out.println(str);
				bw.write(str);
				bw.newLine();
				bw.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
