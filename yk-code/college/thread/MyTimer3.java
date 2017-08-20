package com.yangkai.thread;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimer3 {
	private static boolean flag=false;
	/*在静态方法中定义内部类*/
	public static void main(String[] args) {
		class MyTimerTask extends TimerTask{
		
			@Override  
			public void run() {
				System.out.println("lavender");
				if(flag==false){
					new Timer().schedule(new MyTimerTask() ,2000);	
					flag=true;
				}else{
					new Timer().schedule(new MyTimerTask() ,4000);	
					flag=false;
				}
			}
		}
		new Timer().schedule(new MyTimerTask(),0);
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Calendar.getInstance().get(Calendar.SECOND));
		}
		
	}
}

