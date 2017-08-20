package com.yangkai.se;

import com.yangkai.vo.User;

public class CodeDemo {

	public CodeDemo() {
		
	}
	User user1=new User();
	{System.out.println("CodeDemo:fuck you ");}
	{System.out.println("CodeDemo:"+user1);}
    static{
    	User user2=new User();
    	System.out.println("CodeDemo:static:"+user2);
    	System.out.println("CodeDemo:static:fuck you");
    }
	public static void main(String[] args) {
		//CodeDemo demo=new CodeDemo();
		//System.out.println("main:fuck you");
		for(int i=0;i<4;i++){
			new ThreadCodeDemo().run();
		}
	}
}
class ThreadCodeDemo extends Thread
{
	@Override
	public void run() {
		
		Demo demo=new Demo();
	}
}
class Demo{
	public Demo(){
		
	}
	User user3=new User();
	{System.out.println("Demo:fuck you ");}
	{System.out.println("Demo:"+user3);}
    static{
    	User user4=new User();
    	System.out.println("Demo:static:"+user4);
    	System.out.println("Demo:static:fuck you");
    }
}
