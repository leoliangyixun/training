package com.yangkai.io;
import java.io.*;
public class PipedStreamDemo
{
	public static void main(String[] args)
	{
		PipedInputStream pis=new PipedInputStream();
		PipedOutputStream pos=new PipedOutputStream();
		try{
			pis.connect(pos);
			Producer producer=new Producer(pos);
			Customer customer=new Customer(pis);
			producer.start();
			customer.start();
		}
		catch(Exception e){
		e.printStackTrace();
		}
	}
}
class Producer extends Thread
{
	private PipedOutputStream pos;
	public Producer(PipedOutputStream pos)
	{
		this.pos=pos;
	}
	public void run()
	{
		try{
			pos.write("Hello World!!!".getBytes());
			pos.close();
		}catch(Exception e){
		e.printStackTrace();
		}
	}
}
class Customer extends Thread
{
	private PipedInputStream pis;
	public Customer(PipedInputStream pis)
	{
		this.pis=pis;
	}
	public void run()
	{
		try{
			byte[] buff=new byte[100];
			int len=pis.read(buff);
			System.out.println(new String(buff,0,len));
			pis.close();
		}catch(Exception e){
		e.printStackTrace();
		}
	}
}