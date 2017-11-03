package com.yangkai.bankqueue;

import java.util.ArrayList;
import java.util.List;

public class VipCustomerNumberManager {
	private static VipCustomerNumberManager vipCustomerNumberManager;
	private List<Integer> numberList = new ArrayList<Integer>();
	private Integer customerNumber = 0;
	private VipCustomerNumberManager() {
		
	}
	
	public static VipCustomerNumberManager getVipCustomer(){
		if(vipCustomerNumberManager==null){
			synchronized(VipCustomerNumberManager.class){
				if(vipCustomerNumberManager==null){
					vipCustomerNumberManager=new VipCustomerNumberManager();
				}
			}
		}
		return vipCustomerNumberManager;
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
//		System.out.println("VIP客户人数："+numberList.size());
	}
	
}
