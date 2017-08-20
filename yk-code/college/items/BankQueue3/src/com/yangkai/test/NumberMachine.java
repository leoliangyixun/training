package com.yangkai.test;
import com.yangkai.bankqueue.NumberManager;

public class NumberMachine {

	private static NumberMachine numberMachine=null;
//	private NumberManager numberManager=new NumberManager();
	private static NumberManager numberManager=new NumberManager();
	private NumberMachine() {
		
	}
	public static NumberMachine getNumberMechine(){
		if(numberMachine==null)	{
			synchronized(NumberMachine.class){
				if(numberMachine==null){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					numberMachine=new NumberMachine();
				}
			}
		}
		return numberMachine;
	}
	
//	public NumberManager getNumberManager(){
//		return numberManager;
//	}
	
	public static NumberManager getNumberManagerInstance(){
		return numberManager;
	}
	
	public static void main(String[] args) {
		for(int i=0;i<100;i++){
			new Thread(new Runnable(){
	
				@Override
				public void run() {
					NumberManager numberManager=NumberMachine.getNumberManagerInstance();
					System.out.println(numberManager);
				}
			}).start();
		}
	}

}
