package com.yangkai.bankqueue;

import java.util.Random;

public class QuickServiceWindow implements ServiceWindow{
	private Integer windowNumber;
	public QuickServiceWindow() {
		
	}
	public void setWindowNumber(Integer windowNumber){
		this.windowNumber=windowNumber;
	}
	@Override
	public void service() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out.println(windowNumber + "�ſ��ٴ��ڿ�ʼ�к�");
					Integer quickCutomerNumber = QuickCustomerNumberManager.getQuickCustomer().getNumber();
					if (quickCutomerNumber != null) {
						System.out.println(windowNumber + "�ſ��ٴ��ڿ�ʼΪ"
								+ quickCutomerNumber + "�ſ��ٿͻ�����......");
						try {
							Thread.sleep(Constants.MIN_SERVICE_TIME * 1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(windowNumber + "�ſ��ٴ���Ϊ"
								+ quickCutomerNumber + "�ſ��ٿͻ�������"+Constants.MAX_SERVICE_TIME+"s");
					} else {
						System.out.println(windowNumber + "�ſ��ٴ���û�не����ٿͻ��ţ�����Ϊ��ͨ�û��ṩ����");
						Integer commonCutomerNumber = CommonCustomerNumberManager.getCommonCustomer().getNumber();
						if (commonCutomerNumber != null) {
							System.out.println(windowNumber + "�ſ��ٴ��ڿ�ʼΪ"+ commonCutomerNumber + "����ͨ�ͻ�����");
							int serviceTime=new Random()
								.nextInt(Constants.MAX_SERVICE_TIME - Constants.MIN_SERVICE_TIME + 1) 
								+ Constants.MIN_SERVICE_TIME;
							try {
								Thread.sleep(serviceTime * 1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							System.out.println(windowNumber + "����ͨ����Ϊ"
									+ commonCutomerNumber + "����ͨ�ͻ�������"+serviceTime+"s");
						}else{
							int waitTime = new Random().nextInt(Constants.MAX_WINDOW_WAIT_TIME) + 1;
							System.out.println(windowNumber + "�ſ��ٴ���Ҳû�не���ͨ�ͻ��ţ��ȴ�"
									+ waitTime + "s");
							try {
								Thread.sleep(waitTime * 1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}

		}).start();

	}

}
