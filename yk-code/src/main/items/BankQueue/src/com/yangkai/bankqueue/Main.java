package com.yangkai.bankqueue;

public class Main {

	public Main() {
		
	}

	public static void main(String[] args) {
		//�����ͻ��ú��̡߳�
		NumberMachine.getNumberMechine().start();
		//����4����ͨ�����̡߳�
		for(int i=1;i<=4;i++){
			ServiceWindow commonWindow=new CommonServiceWindow();
			commonWindow.setWindowNumber(i);
			commonWindow.service();
		}
		//����1�����ٴ����̡߳�
		ServiceWindow quickWindow=new QuickServiceWindow();
		quickWindow.setWindowNumber(5);
		quickWindow.service();
		//����1��VIP�����̡߳�
		ServiceWindow vipWindow=new VipServiceWindow();
		vipWindow.setWindowNumber(6);
		vipWindow.service();
	}

}
