package org.yangkai.jdbc;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.yangkai.jdbc.tools.ConnectionTool;

public class ClobDemo {
	public static Connection conn=null;
	public static PreparedStatement ps=null;
	public static ResultSet rs=null;
	public ClobDemo() 
	{
		
	}


	public static void main(String[] args) 
	{
		/*
		File r=new File("clob.txt");
		File w=new File("clob_bak.txt");
		try {
			FileReader fr=new FileReader(r);
			FileWriter fw=new FileWriter(w);
			char[] buff=new char[512];
			while(fr.read(buff)!=-1)
			{
				fw.write(buff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		//writeClob();
		readClob();
	}
	public static void readClob()
	{
		conn=ConnectionTool.getConnection();
		Clob clob=null;
		Reader reader=null;
		BufferedReader br=null;
		String sql="SELECT content FROM clob";
		//BufferedWriter bw=new BufferedWriter(null);
		try {
			ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs=ps.executeQuery();
			if(rs.next())
			{
				clob=rs.getClob("content");
				reader=clob.getCharacterStream();
				char[] buff=new char[512];
				StringBuffer sr=new StringBuffer();
				try {
					while(reader.read(buff)!=-1)
					{
						sr.append(buff);
					}
					System.out.println(sr.toString());
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void writeClob()
	{
		conn=ConnectionTool.getConnection();
		
		File r=new File("clob.txt");
		System.out.println(r.length());
		FileReader fr=null;
		FileInputStream fis=null;
		BufferedReader br=null;
		BufferedInputStream bis=null;
		try {
			fr=new FileReader(r);
			fis=new FileInputStream(r);
			br=new BufferedReader(fr);
			bis=new BufferedInputStream(fis);
			String sql="INSERT INTO clob(content) VALUES(?)";
			try {
				ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				ps.setClob(1, br);
				//ps.setClob(1, br,r.length());
				//ps.setCharacterStream(1, br, r.length());
				ps.setBinaryStream(1, bis,r.length());
				int count=ps.executeUpdate();
				br.close();//一定要关闭流。
				bis.close();
				if(count>0)
				{
					System.out.println("插入大文本成功");
				}else{
					System.out.println("插入大文本失败");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
