package com.yangkai.thread;

public class MultiThread{
	public static void main(String[] args)
	{
		StoreData d=new StoreData();
		Producer p=new Producer(d);
		Consumer c=new Consumer(d);
		p.start();
		c.start();
		
		//System.out.println("main()");
		
	}

}
class Producer extends Thread{
    StoreData d;
	public Producer(StoreData d)
	{
		this.d=d;
	}
	public void run()
	{
		for(int i=0;i<10;i++)
		{
			System.out.println("set:"+i);
			d.setValue(i);
			
			//System.out.println("set:"+i);
		}
	}

}
class Consumer extends Thread{
    StoreData d;
	public Consumer(StoreData d)
	{
		this.d=d;
	}
	public void run()
	{
		while(true)
		{
			System.out.println("get:"+d.getValue());
		}
	}
}
class StoreData{
	int value;
	boolean FULL=false;
	public synchronized void setValue(int value)
	{
		if(FULL==false)
		{
			//System.out.println("set:");
			this.value=value;
			//System.out.println("set:");
			FULL=true;
			notify();
		}
		try{
			wait();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public synchronized int getValue()
	{
		if(FULL==false)
		{
			try{
				wait();
		}catch(Exception e){
			e.printStackTrace();
		}
		}
		FULL=false;
		notify();
		return value;
		
	}
}
