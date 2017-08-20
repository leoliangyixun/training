package com.yangkai.thread;

public class DrawMoney {

	public static void main(String[] args) 
	{
		Account account=new Account(0);
		DrawThread drawor1=new DrawThread("取钱者甲",account,800);
		DepositThread depositor1=new DepositThread("存钱者甲",account,800);
		DrawThread drawor2=new DrawThread("取钱者乙",account,800);
		DepositThread depositor2=new DepositThread("存钱者乙",account,800);
		DrawThread drawor3=new DrawThread("取钱者丙",account,800);
		DepositThread depositor3=new DepositThread("存钱者丙",account,800);
		/*
		 * 开启三对存取钱进程。
		 * */
		drawor1.start();
		depositor1.start();
		drawor2.start();
		depositor2.start();
		drawor3.start();
		depositor3.start();

	}

}
class DrawThread extends Thread{
	Account account=null;
	double money=0;
	public DrawThread (String name,Account account,double money)
	{
		super(name);
		this.account=account;
		this.money=money;
	}
	@Override
	public void run() 
	{
		for(int i=0;i<20;i++)
		{
			account.draw(money);
		}
	}
	
}
class DepositThread extends Thread{
	
	Account account=null;
	double money=0;
	public DepositThread (String name,Account account,double money)
	{
		super(name);
		this.account=account;
		this.money=money;
	}
	@Override
	public void run() 
	{
		for(int i=0;i<20;i++)
		{
			account.deposit(money);
		}
	}
}
class Account{
	private double balence=0.0;
	boolean hasMoney=false;
	public Account(double balence){
		this.balence=balence;
	}
	public double getBalence()
	{
		return balence;
	}
	public synchronized void draw(double money)
	{
		if(hasMoney==true)
		{
			balence-=money;
			System.out.println(Thread.currentThread().getName()+"取款"+money+"元");
			System.out.println("账户余额为："+balence);
			hasMoney=false;
			notifyAll();
		}
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public synchronized void deposit(double money)
	{
		if(hasMoney==false)
		{
			balence+=money;
			System.out.println(Thread.currentThread().getName()+"存款"+money+"元");
			System.out.println("账户余额为："+balence);
			hasMoney=true;
			notifyAll();
		}
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
