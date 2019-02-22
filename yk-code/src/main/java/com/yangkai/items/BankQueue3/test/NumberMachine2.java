package com.yangkai.test;

import com.yangkai.bankqueue.NumberManager;

public class NumberMachine2 {
	private static NumberMachine2 numberMachine=null;
	private NumberManager commonNumberManager=new NumberManager();
	private NumberManager quickNumberManager=new NumberManager();
	private NumberManager vipNumberManager=new NumberManager();
	private NumberMachine2() {
		
	}
	public static NumberMachine2 getNumberMechine(){
		if(numberMachine==null)	{
			synchronized(NumberMachine.class){
				if(numberMachine==null){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					numberMachine=new NumberMachine2();
				}
			}
		}

//		synchronized(NumberMachine.class){
//			if(numberMachine==null){
//				try {
//					Thread.sleep(10);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				numberMachine=new NumberMachine();
//			}
//		}
		
//		if(numberMachine==null){
//			try {
//				Thread.sleep(10);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			numberMachine=new NumberMachine();
//		}
		return numberMachine;
	}
	
	public NumberManager getCommonNumberManager(){
		return commonNumberManager;
	}
	
	public NumberManager getQuickNumberManager(){
		return quickNumberManager;
	}
	
	public NumberManager getVIPNumberManager(){
		return vipNumberManager;
	}
	
//	public static void main(String[] args) {
//		long startTime=System.currentTimeMillis();
//		for(int i=0;i<100;i++){
//			new Thread(new Runnable(){
//
//				@Override
//				public void run() {
//					
//					NumberMachine numberMachine=NumberMachine.getNumberMechine();
//					System.out.println(Thread.currentThread().getName()+": "+numberMachine);
//					System.out.println(numberMachine);
//				}
//			}).start();
//		}
//		long endTime=System.currentTimeMillis();
//		System.out.println("cost: "+(endTime-startTime));
//	}
}