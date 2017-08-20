package com.yangkai.bankqueue;

public class NumberManager {
	private CustomerType customerType;
	public NumberManager() {

	}
	public void setCustomerType(CustomerType customerType){
		this.customerType=customerType;
	}
	
	public void addNumber(){
		switch(customerType){
			case COMMON:
				CommonCustomerNumberManager.getCommonCustomer().addNumber();break;
			case QUICK:
				QuickCustomerNumberManager.getQuickCustomer().addNumber();break;
			case VIP:
				VipCustomerNumberManager.getVipCustomer().addNumber();break;
		}
	}

}
