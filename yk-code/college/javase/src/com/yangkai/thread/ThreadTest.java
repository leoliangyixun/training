package com.yangkai.thread;
public class ThreadTest{
	public static void main(String[] args) throws Exception
	{
		//MyThread mythread=new MyThread("yangkai");
		MyThread mythread=new MyThread();
		mythread.start();
	}
}
class MyThread extends Thread{
	public MyThread()
	{
		
	}
	public MyThread(String name)
	{
		super(name);
	}
	public void run()
	{
		System.out.println(getName());
	}
}
