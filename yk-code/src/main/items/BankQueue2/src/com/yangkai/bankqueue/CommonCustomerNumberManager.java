package com.yangkai.bankqueue;

import java.util.ArrayList;
import java.util.List;

public class CommonCustomerNumberManager {
	private static CommonCustomerNumberManager commonCustomerNumberManager;
	public List<Integer> numberList = new ArrayList<Integer>();
	private Integer customerNumber = 0;
	private CommonCustomerNumberManager() {
		
	}
	
	public static CommonCustomerNumberManager getCommonCustomer(){
		if(commonCustomerNumberManager==null)	{
			synchronized(CommonCustomerNumberManager.class){
				if(commonCustomerNumberManager==null){
					commonCustomerNumberManager=new CommonCustomerNumberManager();
				}
			}
		}
		return commonCustomerNumberManager;
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
	}
	
}
