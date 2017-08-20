package com.yangkai.bankqueue;

import java.util.ArrayList;
import java.util.List;

public class QuickCustomerNumberManager {
	private static QuickCustomerNumberManager quickCustomerNumberManager;
	private List<Integer> numberList = new ArrayList<Integer>();
	private Integer customerNumber = 0;
	private QuickCustomerNumberManager() {
		
	}
	
	public static QuickCustomerNumberManager getQuickCustomer(){
		if(quickCustomerNumberManager==null){
			synchronized(QuickCustomerNumberManager.class){
				if(quickCustomerNumberManager==null){
					quickCustomerNumberManager=new QuickCustomerNumberManager();
				}
			}
		}
		return quickCustomerNumberManager;
	}
	
	public synchronized Integer getNumber() {
		if (numberList.size() > 0) {
			return numberList.remove(0);
		} else {
			return null;
		}
	}
	
	public synchronized void addNumber(){
		numberList.add(++customerNumber);
//		System.out.println("快速客户人数："+numberList.size());
	}
	
}
