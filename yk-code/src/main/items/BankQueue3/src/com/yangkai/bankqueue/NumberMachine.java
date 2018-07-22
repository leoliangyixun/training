package com.yangkai.bankqueue;

public class NumberMachine {
	private static NumberMachine numberMachine;
	
	private NumberManager commonNumberManager=new CommonNumberManager();
	private NumberManager quickNumberManager=new QuickNumberManager();
	private NumberManager vipNumberManager=new VipNumberManager();

	private NumberMachine() {

	}
	public static NumberMachine getNumberMechine(){
		if(numberMachine==null)	{
			synchronized(NumberMachine.class){
				if(numberMachine==null){
					numberMachine=new NumberMachine();
				}
			}
		}
		return numberMachine;
	}
	
	public NumberManager getCommonNumberManager(){
		return commonNumberManager;
	}
	
	public NumberManager getQuickNumberManager(){
		return quickNumberManager;
	}
	
	public NumberManager getVipNumberManager(){
		return vipNumberManager;
	}
	
	public void start(){
		new Thread(new Runnable(){

			@Override
			public void run() {
				while (true) {
					
				}
			}
			
		}).start();
	}
	
}
