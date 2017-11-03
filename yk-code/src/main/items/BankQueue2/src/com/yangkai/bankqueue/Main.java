package com.yangkai.bankqueue;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

	public Main() {
		
	}

	public static void main(String[] args) {
		//����3���ͻ��ú��̡߳�
		List<NumberMachine> numberMachines=NumberMachine.getNumberMachines(3);
		for(int i=0;i<numberMachines.size();i++){
			numberMachines.get(i).start();
		}
		//����4����ͨ�����̡߳�
		for(int i=1;i<=4;i++){
			ServiceWindow commonWindow=new CommonServiceWindow();
			commonWindow.setWindowNumber(i);
			commonWindow.service();
		}
		//����1�����ٴ����̡߳�
		ServiceWindow quickWindow1=new QuickServiceWindow();
		quickWindow1.setWindowNumber(5);
		quickWindow1.service();
//		
//		ServiceWindow quickWindow2=new QuickServiceWindow();
//		quickWindow2.setWindowNumber(8);
//		quickWindow2.service();
		//����1��VIP�����̡߳�
		ServiceWindow vipWindow=new VipServiceWindow();
		vipWindow.setWindowNumber(6);
		vipWindow.service();
//		new Timer().schedule(new TimerTask(){
//
//			@Override
//			public void run() {
//				System.out.println("size: "+CommonCustomerNumberManager.getCommonCustomer().numberList.size());
//			}
//			
//		}, 0,2000);
	}

}
