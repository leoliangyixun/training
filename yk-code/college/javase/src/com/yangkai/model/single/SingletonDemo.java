package com.yangkai.model.single;
public class SingletonDemo{
	public static void main(String[] args){
	/*
		for(int i=0;i<100;i++){
			Singleton single=Singleton.getInstance();
			System.out.println(single);
		}
		*/
		for(int i=0;i<100;i++)
		{
			new SingletonThreadDemo().start();
		}
	}
}
class SingletonThreadDemo extends Thread{
	public void run(){
		try{
			Thread.sleep(10);
		}catch(Exception e){
			e.printStackTrace();
		}
		Singleton single=Singleton.getInstance();
		System.out.println(single);
	}
}