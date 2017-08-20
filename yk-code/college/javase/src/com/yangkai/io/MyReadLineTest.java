package com.yangkai.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyReadLineTest {
	public static void main(String[] args) { 
		try {
			FileReader fr=new FileReader("Stream.java");
			InputStreamReader in=new InputStreamReader(System.in);
			MyReadLine myReadLine=new MyReadLine(in);
			//MyReadLine mybr=new MyReadLine();
			
				String str=myReadLine.myReadLine();
				System.out.println(str);
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
