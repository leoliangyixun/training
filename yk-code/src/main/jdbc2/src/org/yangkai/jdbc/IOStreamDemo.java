package org.yangkai.jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IOStreamDemo {
	private static Connection conn = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	String url = "jdbc:mysql://localhost:3306/text?user=root&password=root";

	public IOStreamDemo() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void writeToDB() {
		//将BookListDemo.java中的内容写到数据库。
		String sql = "INSERT INTO big_text(content) VALUES(?)";
		try {
			File file = new File("src/com/yangkai/jdbc/test.txt");
			BufferedReader rd = null;
			try {
				rd = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setCharacterStream(1, rd, file.length());
			
			try {
				rd.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			int i = pstmt.executeUpdate();
			if (i > 0) {
				System.out.println("插入成功");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void readFromDB() 
	{
		//将数据库中的内容写到BookListDemoBak.java文件中
		String sql = "SELECT content FROM big_text WHERE id=1";
		Reader rd = null;
		StringReader sr=null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				char[] temp_buff = new char[2048];
				rd = rs.getCharacterStream("content");// 该方法返回一个Reader对象
				FileWriter fw = null;
				try {
					fw = new FileWriter("test.txt");
				} catch (IOException e) {
					e.printStackTrace();
				}
				//BufferedWriter buff = new BufferedWriter(fw);
			
				try {
					int len = rd.read(temp_buff);
					// buff.write(new String(temp_buff, 0, len));
					//buff.write(temp_buff, 0, len);
					//buff.close();
					fw.write(new String(temp_buff, 0, len));
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) 
	{
		IOStreamDemo d = new IOStreamDemo();
		//d.writeToDB();
		d.readFromDB();

	}

}
