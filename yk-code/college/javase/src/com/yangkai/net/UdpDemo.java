package com.yangkai.net;

import java.io.BufferedReader;
import java.io.Reader;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpDemo {

	public UdpDemo() {
		
	}


	public static void main(String[] args) {
	

	}

}
class UdpSend{
	public static void main(String[] args){
		try {
			DatagramSocket ds=new DatagramSocket();
			//BufferedReader br=new BufferedReader();
			//��������Ķ����ַ�����
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
}
class UdpReceive{
	
}