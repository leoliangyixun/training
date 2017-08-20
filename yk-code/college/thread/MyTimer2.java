package com.yangkai.thread;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimer2 {
	static class MyTimerTask extends TimerTask{
		private static boolean flag=false;
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
	public static void main(String[] args) {
		
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

