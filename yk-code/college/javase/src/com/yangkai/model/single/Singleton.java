package com.yangkai.model.single;
public class Singleton{

	private static Singleton single;
	private Singleton(){
	}
	public static Singleton getInstance(){
		if(single==null) {
			single=new Singleton();
			}
			return single;
	}
	
	/*
	private static Singleton single=new Singleton();
	private Singleton(){
	}
	public static Singleton getInstance(){
		return single;
	}
	*/
}
