package com.yangkai.bankqueue;

import java.util.Random;

public class NumberMachine {
	private static NumberMachine numberMachine;

//	private NumberManager commonNumberManager;
//	private NumberManager quickNumberManager;
//	private NumberManager vipNumberManager;
	
	private NumberManager commonNumberManager=new NumberManager();
	private NumberManager quickNumberManager=new NumberManager();
	private NumberManager vipNumberManager=new NumberManager();

	private NumberMachine() {
//		commonNumberManager=new NumberManager();
//		quickNumberManager=new NumberManager();
//		vipNumberManager=new NumberManager();
	}
	public static NumberMachine getNumberMechine(){
		if(numberMachine==null)	{
			synchronized(NumberMachine.class){
				if(numberMachine==null){
//					try {
//						Thread.sleep(10);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
					numberMachine=new NumberMachine();
				}
			}
		}
		return numberMachine;
	}
	
	public NumberManager getCommonNumberManager(){
//		commonNumberManager.setCustomerType(CustomerType.COMMON);
		return commonNumberManager;
	}
	
	public NumberManager getQuickNumberManager(){
//		quickNumberManager.setCustomerType(CustomerType.Quick);
		return quickNumberManager;
	}
	
	public NumberManager getVipNumberManager(){
//		vipNumberManager.setCustomerType(CustomerType.VIP);
		return vipNumberManager;
	}
	
	private Integer generateNumber() {
		return new Random().nextInt(10);
	}
	
	private CustomerType getCustomerType(int generateNumber){
		if (generateNumber == 0) {
			return CustomerType.VIP;
		} else if (generateNumber == 1|| generateNumber == 2
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
								int commonCustomerNumber=getCommonNumberManager().addNumber();
								System.out.println(commonCustomerNumber+"号普通客户正在等待服务......");
								break;
							case QUICK:
								int quickCustomerNumber=getQuickNumberManager().addNumber();
								System.out.println(quickCustomerNumber+"号快速客户正在等待服务......");
								break;
							case VIP:
								int vipCustomerNumber=getVipNumberManager().addNumber();
								System.out.println(vipCustomerNumber+"号VIP客户正在等待服务......");
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
	
//	public static void main(String[] args) {
//		for(int i=1;i<=100;i++){
//			new Thread(new Runnable(){
//				
//				@Override
//				public void run() {
//					
//					System.out.println(NumberMachine.getNumberMechine().getCommonNumberManager());
//				}
//				
//			}).start();
//		}
//	}
	
}
