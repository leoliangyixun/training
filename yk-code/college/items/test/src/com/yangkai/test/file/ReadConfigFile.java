package com.yangkai.test.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

import com.yangkai.test.domain.Point;

public class ReadConfigFile {

	public ReadConfigFile() {
		
	}

	public static void main(String[] args) {
		InputStream ins;
//		FileInputStream ins;
		try {
//			ins=new FileInputStream("config.properties");
			ins = new FileInputStream("config.properties");
			Properties prop=new Properties();
			prop.load(ins);
			String className=prop.getProperty("className");
			System.out.println(className);
			@SuppressWarnings("unchecked")
			Collection<Point> col=(Collection<Point>)Class.forName(className).newInstance();
			Point p1=new Point(1,1,1);
			Point p2=new Point(1,2,1);
			Point p3=new Point(1,1,1);
			col.add(p1);
			col.add(p2);
			col.add(p3);
			System.out.println(col);
			ins.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 

	}

}
