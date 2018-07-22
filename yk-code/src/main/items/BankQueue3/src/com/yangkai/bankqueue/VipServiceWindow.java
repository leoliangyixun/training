package com.yangkai.bankqueue;

import java.util.Random;

public class VipServiceWindow implements ServiceWindow {
	private Integer windowNumber;

	public VipServiceWindow() {

	}

	public void setWindowNumber(Integer windowNumber) {
		this.windowNumber = windowNumber;
	}

	@Override
	public void service() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out.println(windowNumber + "��VIP���ڿ�ʼ�к�");
					Integer vipCustomerNumber = NumberMachine.getNumberMechine()
							.getVipNumberManager().getNumber();		
					if (vipCustomerNumber != null) {
						System.out.println(windowNumber + "��VIP���ڿ�ʼΪ"+ vipCustomerNumber 
								+ "��VIP�ͻ�����......");	
						int serviceTime = new Random()
								.nextInt(Constants.MAX_SERVICE_TIME- Constants.MIN_SERVICE_TIME + 1)
								+ Constants.MIN_SERVICE_TIME;	
						try {
							Thread.sleep(serviceTime * 1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(windowNumber + "��VIP����Ϊ"
								+ vipCustomerNumber + "��VIP�ͻ�������" + serviceTime+ "s");
								
					} else {
						System.out.println(windowNumber + "��VIP����û�не�VIP�ͻ��ţ�����Ϊ��ͨ�û��ṩ����");
						Integer commonCustomerNumber = NumberMachine.getNumberMechine()
							.getCommonNumberManager().getNumber();
						if (commonCustomerNumber != null) {
							System.out.println(windowNumber + "��VIP���ڿ�ʼΪ"+ commonCustomerNumber + "����ͨ�ͻ�����");
							int serviceTime=new Random()
								.nextInt(Constants.MAX_SERVICE_TIME - Constants.MIN_SERVICE_TIME + 1) 
								+ Constants.MIN_SERVICE_TIME;
							try {
								Thread.sleep(serviceTime * 1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
							System.out.println(windowNumber + "��VIP����Ϊ"
									+ commonCustomerNumber + "����ͨ�ͻ�������"+serviceTime+"s");
						} else {
							int waitTime = new Random()
									.nextInt(Constants.MAX_WINDOW_WAIT_TIME) + 1;
							System.out.println(windowNumber + "��VIP����Ҳû�не���ͨ�ͻ��ţ��ȴ�"+ waitTime + "s");	
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