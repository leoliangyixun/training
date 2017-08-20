package com.yangkai.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpDemo {

	public TcpDemo() {
		
	}
	
	public static void main(String[] args) {

	}
}
class TcpServer{
	public static void main(String[] args) {
		try {
			ServerSocket ss=new ServerSocket(6000);
			Socket s=ss.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
