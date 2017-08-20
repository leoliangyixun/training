package com.yangkai.proxy.test1;

public class DogImpl implements Dog {

	@Override
	public void info() {
		System.out.println("我是一只可爱的小狗。");
	}

	@Override
	public void run() {
		System.out.println("我在奔跑。");
	}
}
