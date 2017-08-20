package com.yangkai.bean.shop;
public class SimpleShopBean {
	private String name;
	private int num;
	private double price;
	private double total;
	public SimpleShopBean(String name)
	{
		this.name=name;
	}
	public SimpleShopBean(String name,int num,double price,double total)
	{
		this.name=name;
		this.num=num;
		this.price=price;
		this.total=total;
	}
	public String getName() 
	{
		return name;
	}
	public int getNum() 
	{
		return num;
	}
	public double getPrice() 
	{
		return price;
	}
	public double getTotal()
	{
		return total;
	}
}
