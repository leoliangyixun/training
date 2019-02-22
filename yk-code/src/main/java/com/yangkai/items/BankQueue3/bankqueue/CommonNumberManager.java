package com.yangkai.bankqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CommonNumberManager implements NumberManager {
	private List<Integer> numberList = new ArrayList<Integer>();
	public CommonNumberManager() {
		
	}

	@Override
	public void start() {
		new Thread(new Runnable(){

			@Override
			public void run() {
				new Timer().schedule(new TimerTask(){

					@Override
					public void run() {
						int customerNumber=new NumberGenerator().generateNumber();
						numberList.add(customerNumber);
					}
				}, 0, 1000);
				
			}
			
		}).start();
		
	}

}
