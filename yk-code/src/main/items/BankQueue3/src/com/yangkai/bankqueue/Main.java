package com.yangkai.bankqueue;

public class Main {

	public Main() {
		
	}

	public static void main(String[] args) {
		//开启客户拿号线程。
		NumberMachine.getNumberMechine().start();
		//开启4个普通窗口线程。
		for(int i=1;i<=4;i++){
			ServiceWindow commonWindow=new CommonServiceWindow();
			commonWindow.setWindowNumber(i);
			commonWindow.service();
		}
		//开启1个快速窗口线程。
		ServiceWindow quickWindow=new QuickServiceWindow();
		quickWindow.setWindowNumber(5);
		quickWindow.service();
		//开启1个VIP窗口线程。
		ServiceWindow vipWindow=new VipServiceWindow();
		vipWindow.setWindowNumber(6);
		vipWindow.service();
	}

}
