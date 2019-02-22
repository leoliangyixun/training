package com.yangkai.bean;

public class StudentBean 
{	
	String name;
	Double cheng;
	public StudentBean()
	{
		name="�";
		cheng=80.0;		
	}
	public void setname(String username)
	{name=username;}
	public void setcheng(Double usercheng)
	{cheng=usercheng;}
	public String getname()
	{return name;}
	public Double getcheng()
	{return cheng;}
}
