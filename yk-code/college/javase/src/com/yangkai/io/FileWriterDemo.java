package com.yangkai.io;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterDemo {
	
	public static void main(String[] args) {
	try {
		FileReader fr=new FileReader("4.txt");
		BufferedReader br=new BufferedReader(fr);
		char[] buff=new char[10240];
		int len=br.read(buff);
		br.close();
		FileWriter fw = new FileWriter("4_bak.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write(buff, 0, len);
		bw.close();
	} catch (IOException e) {
		e.printStackTrace();
	}	
	}
}
