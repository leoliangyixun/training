package com.yangkai.proxy.test1;

public class DogImpl implements Dog {

	@Override
	public void info() {
		System.out.println("����һֻ�ɰ���С����");
	}

	@Override
	public void run() {
		System.out.println("���ڱ��ܡ�");
	}
}
