package com.yangkai.model.adapter;
interface Target{
	public void request();
}
interface MyTarget{
	public void myRequest();
}

public class AdapterDemo {

	public static void main(String[] args) {
		MyTarget t=new Adapter();
		t.myRequest();
	}
}

class Adapter extends Adaptee implements MyTarget{

	@Override
	public void myRequest() {
		this.request();
	}
}

class Adaptee implements Target{
	public void request(){
		System.out.println("call request() in Target");
	}

}
