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
					System.out.println(windowNumber + "号快速窗口开始叫号");
					Integer quickCustomerNumber = NumberMachine.getNumberMechine()
							.getQuickNumberManager().getNumber();
					if (quickCustomerNumber != null) {
						System.out.println(windowNumber + "号快速窗口开始为"
								+ quickCustomerNumber + "号快速客户服务......");
						try {
							Thread.sleep(Constants.MIN_SERVICE_TIME * 1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(windowNumber + "号快速窗口为"
								+ quickCustomerNumber + "号快速客户服务了"+Constants.MAX_SERVICE_TIME+"s");
					} else {
						System.out.println(windowNumber + "号快速窗口没有叫到快速客户号，可以为普通用户提供服务");
						Integer commonCustomerNumber = NumberMachine.getNumberMechine()
							.getCommonNumberManager().getNumber();
						if (commonCustomerNumber != null) {
							System.out.println(windowNumber + "号快速窗口开始为"+ commonCustomerNumber + "号普通客户服务");
							int serviceTime=new Random()
								.nextInt(Constants.MAX_SERVICE_TIME - Constants.MIN_SERVICE_TIME + 1) 
								+ Constants.MIN_SERVICE_TIME;
							try {
								Thread.sleep(serviceTime * 1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							System.out.println(windowNumber + "号快速窗口为"
									+ commonCustomerNumber + "号普通客户服务了"+serviceTime+"s");
						}else{
							int waitTime = new Random().nextInt(Constants.MAX_WINDOW_WAIT_TIME) + 1;
							System.out.println(windowNumber + "号快速窗口也没有叫到普通客户号，等待"
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
