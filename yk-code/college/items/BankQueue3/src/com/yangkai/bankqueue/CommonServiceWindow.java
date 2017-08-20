package com.yangkai.bankqueue;

import java.util.Random;

public class CommonServiceWindow implements ServiceWindow {
	private Integer windowNumber;

	public CommonServiceWindow() {

	}

	public void setWindowNumber(Integer windowNumber) {
		this.windowNumber = windowNumber;
	}

	@Override
	public void service() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// 不断取号。
				while (true) {
					System.out.println(windowNumber + "号普通窗口开始叫号");
					Integer commonCustomerNumber = NumberMachine.getNumberMechine()
							.getCommonNumberManager().getNumber();
					if (commonCustomerNumber != null) {
						System.out.println(windowNumber + "号普通窗口开始为"
								+ commonCustomerNumber + "号普通客户服务......");
						int serviceTime=new Random()
							.nextInt(Constants.MAX_SERVICE_TIME - Constants.MIN_SERVICE_TIME + 1) 
							+ Constants.MIN_SERVICE_TIME;
						try {
							Thread.sleep(serviceTime * 1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(windowNumber + "号普通窗口为"
								+ commonCustomerNumber + "号普通客户服务了"+serviceTime+"s");
					} else {
						int waitTime = new Random().nextInt(Constants.MAX_WINDOW_WAIT_TIME) + 1;
						System.out.println(windowNumber + "号普通窗口没有叫到号，等待"
								+ waitTime + "s");
						try {
							Thread.sleep(waitTime * 1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}

		}).start();

	}

}
