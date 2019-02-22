package com.yangkai.bankqueue;

public class NumberGenerator {
//	private List<Integer> numberList = new ArrayList<Integer>();
	private Integer customerNumber = 0;
//	public NumberGenerator() {
//		
//	}
//	public synchronized Integer getNumber() {
//		if (numberList.size() > 0) {
//			return numberList.remove(0);
//		} else {
//			return null;
//		}
//	}
//	
//	public Integer addNumber(){
//		numberList.add(++customerNumber);
//		return customerNumber;
//	}
	
	public synchronized Integer generateNumber(){
		return ++customerNumber;
	}
	
}
