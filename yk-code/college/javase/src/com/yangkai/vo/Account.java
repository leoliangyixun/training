package com.yangkai.vo;

public class Account {
	
	private int id;
	private String name;
	private float balance;
	public Account() {
		
	}
	public Account(String name) {
		this.name=name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
@Override
public String toString() {
	return "id="+this.id+";name="+this.name+";balance="+this.balance;
}
	
}
