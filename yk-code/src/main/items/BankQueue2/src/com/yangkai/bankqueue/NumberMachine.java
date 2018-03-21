package com.yangkai.bankqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberMachine {
	private static List<NumberMachine> numberMachines=new ArrayList<NumberMachine>();
	private NumberManager commonNumberManager=new NumberManager();
	private NumberManager quickNumberManager=new NumberManager();
	private NumberManager vipNumberManager=new NumberManager();

	private NumberMachine() {

	}
	
	public static List<NumberMachine> getNumberMachines(int num){
		if(numberMachines.size()==0){
			synchronized(NumberMachine.class){
				if(numberMachines.size()==0){
					for(int i=0;i<num;i++){
						numberMachines.add(new NumberMachine());
					}
				}
			}
		}	
		return numberMachines;
	}
	
	public NumberManager getCommonNumberManager(){
		commonNumberManager.setCustomerType(CustomerType.COMMON);
		return commonNumberManager;
	}
	
	public NumberManager getQuickNumberManager(){
		quickNumberManager.setCustomerType(CustomerType.QUICK);
		return quickNumberManager;
	}
	
	public NumberManager getVipNumberManager(){
		vipNumberManager.setCustomerType(CustomerType.VIP);
		return vipNumberManager;
	}
	
	private Integer generateNumber() {
		return new Random().nextInt(10);
	}
	
	private CustomerType getCustomerType(int generateNumber){
		if (generateNumber == 0) {
			return CustomerType.VIP;
		} else if (generateNumber == 1 || generateNumber == 2
				|| generateNumber == 3) {
			return CustomerType.QUICK;
		} else if (generateNumber == 4 || generateNumber == 5
				|| generateNumber == 6 || generateNumber == 7
				|| generateNumber == 8 || generateNumber == 9) {
			return CustomerType.COMMON;
		} else {
			return null;
		}
	}
	public void start(){
		new Thread(new Runnable(){

			@Override
			public void run() {
				while (true) {
					int generateNumber = generateNumber();
					CustomerType customerType=getCustomerType(generateNumber);
					if(customerType!=null){
						switch(customerType){
							case COMMON:
								getCommonNumberManager().addNumber();
								break;
							case QUICK:
								getQuickNumberManager().addNumber();
								break;
							case VIP:	
								getVipNumberManager().addNumber();
								break;
						}
					}
					try {
						Thread.sleep((new Random().nextInt(Constants.MAX_CUSTOMER_INTERVAL_TIME) + 1) * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}).start();
	}
}
