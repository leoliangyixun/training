package com.yangkai.thread;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {

	public static void main(String[] args) {
		/*
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("fuck");
				
			}
		},1000,3000);
	*/
		/*
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("fuck");
				new Timer().schedule(new TimerTask() {
					
					@Override
					public void run() {
						System.out.println("fuck");
					}
				},2000);	
			}
		},1000);
		*/
		new Timer().schedule(new MyTimerTask(),0);
		/*
		 * 计时线程。
		 * */
//		new Thread(new Runnable(){
//
//			@Override
//			public void run() {
//				while(true){
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					System.out.println(Calendar.getInstance().get(Calendar.SECOND));
//				}	
//			}	
//		}).start();
		
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
class MyTimerTask extends TimerTask{
	private static boolean flag=false;
	@Override
	public void run() {
		System.out.println("lavender");
		if(flag==false){
			/*
			 * 嵌套产生MyTimerTask对象。
			 * */
			new Timer().schedule(new MyTimerTask() ,2000);	
			flag=true;
		}else{
			new Timer().schedule(new MyTimerTask() ,4000);	
			flag=false;
		}
	}
}
