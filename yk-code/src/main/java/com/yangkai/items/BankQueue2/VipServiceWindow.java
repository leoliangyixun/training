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
					System.out.println(windowNumber + "号VIP窗口开始叫号");
					Integer vipCutomerNumber = VipCustomerNumberManager.getVipCustomer().getNumber();		
					if (vipCutomerNumber != null) {
						System.out.println(windowNumber + "号VIP窗口开始为"+ vipCutomerNumber 
								+ "号VIP客户服务......");	
						int serviceTime = new Random()
								.nextInt(Constants.MAX_SERVICE_TIME- Constants.MIN_SERVICE_TIME + 1)
								+ Constants.MIN_SERVICE_TIME;	
						try {
							Thread.sleep(serviceTime * 1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(windowNumber + "号VIP窗口为"
								+ vipCutomerNumber + "号VIP客户服务了" + serviceTime+ "s");
								
					} else {
						System.out.println(windowNumber + "号VIP窗口没有叫到VIP客户号，可以为普通用户提供服务");
						Integer commonCutomerNumber = CommonCustomerNumberManager.getCommonCustomer().getNumber();
						if (commonCutomerNumber != null) {
							System.out.println(windowNumber + "号VIP窗口开始为"+ commonCutomerNumber + "号普通客户服务");
							int serviceTime=new Random()
								.nextInt(Constants.MAX_SERVICE_TIME - Constants.MIN_SERVICE_TIME + 1) 
								+ Constants.MIN_SERVICE_TIME;
							try {
								Thread.sleep(serviceTime * 1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
							System.out.println(windowNumber + "号VIP窗口为"
									+ commonCutomerNumber + "号普通客户服务了"+serviceTime+"s");
						} else {
							int waitTime = new Random()
									.nextInt(Constants.MAX_WINDOW_WAIT_TIME) + 1;
							System.out.println(windowNumber + "号VIP窗口也没有叫到普通客户号，等待"+ waitTime + "s");	
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
