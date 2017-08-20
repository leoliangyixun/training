package com.yangkai.jdbc;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class IOStreamDemo2 {
	private static Connection conn = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	String url = "jdbc:mysql://localhost:3306/text?user=root&password=root";
	public IOStreamDemo2() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void readFromDB() {
		//�����ݿ��е�����д��BookListDemoBak.java�ļ���
		String sql = "SELECT content FROM big_text WHERE id=1";
		Reader rd = null;
		StringReader sr=null;
		String str=null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				char[] temp_buff = new char[2048];
				rd = rs.getCharacterStream("content");// �÷�������һ��Reader����
				FileWriter fw = null;
				try {
					fw = new FileWriter("test.txt");
				} catch (IOException e) {
					e.printStackTrace();
				}
				BufferedWriter buff = new BufferedWriter(fw);
				try {
					//int len = rd.read(temp_buff);
					str=rd.toString();
					System.out.println(str);//str�ǵ�ַ��������rd�е����ݡ�
					//buff.write(str);
					// buff.write(new String(temp_buff, 0, len));
					//buff.write(temp_buff, 0, len);
					buff.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void writeToDB() {
		
		String sql = "INSERT INTO big_text(content) VALUES(?)";
		try {
			String str="lavender";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, str);
			int i = pstmt.executeUpdate();
			if (i > 0) 
			{
				System.out.println("����ɹ�");
			}
			else
			{
				System.out.println("����ʧ��");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		IOStreamDemo2 d = new IOStreamDemo2();
		//d.readFromDB();
		d.writeToDB();
	}

}
