package com.yangkai.net.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpMultiDemo {

	public TcpMultiDemo() {
		
	}

	public static void main(String[] args) {
		
	}
}
class TcpServer extends Thread{
	private static ServerSocket ss=null;
	public static void main(String[] args) {
		 try {
			ss=new ServerSocket(60000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new TcpServer().start();
	}
	@Override
	public void run() {
		while(true){
			Socket s = null;
			InputStream ins=null;
			BufferedReader br = null;
			try {
				s = ss.accept();
				ins=s.getInputStream();
				br=new BufferedReader(new InputStreamReader(ins));
				String line=null;
				while((line=br.readLine())!=null){
					String ip=s.getInetAddress().getHostAddress();
					System.out.println(ip+"   connected: "+line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					s.close();
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
class TcpClient{
	public static void main(String[] args) {
		Socket s=null;
		BufferedReader br=null;
		BufferedWriter bw=null;
		try {
			s = new Socket(InetAddress.getByName("192.168.16.100"),60008);
			OutputStream ous=s.getOutputStream();
			br=new BufferedReader(new InputStreamReader(System.in));
			bw=new BufferedWriter(new OutputStreamWriter(ous));
			String line=null;
			while((line=br.readLine())!=null){
				if("88".equals(line)) {
					break;
				}
				bw.write(line);
				bw.newLine();
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				br.close();
				bw.close();
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
