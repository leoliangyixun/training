package com.yangkai.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ChatDemo{
	public static void main(String[] agrs)
	{
		new SendMsg().start();
		new ReceiveMsg().start();
	}
}
class SendMsg extends Thread{

	
	public void run()
	{
		DatagramSocket ds=null;
		try {
			 ds=new DatagramSocket(10000);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String data=null;
		try {
			while((data=br.readLine())!=null)
			{
				byte[] buff=data.getBytes();
				DatagramPacket dp=new DatagramPacket(buff,buff.length,InetAddress.getByName("192.168.16.2"),8888);
				ds.send(dp);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
class ReceiveMsg extends Thread{
	public void run()
	{
		DatagramSocket ds=null;
		try {
			ds=new DatagramSocket(8888);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		byte[] buff=new byte[1024];
		DatagramPacket dp=new DatagramPacket(buff,buff.length);
		while(true)
		{
			try {
				ds.receive(dp);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String ip=dp.getAddress().getHostAddress();
			String data=new String(dp.getData(),0,dp.getLength());
			int port=dp.getPort();
			System.out.println("ip:"+ip+"   data:"+data+"   port:"+port);
		}
	}
}