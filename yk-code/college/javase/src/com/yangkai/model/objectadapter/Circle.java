package com.yangkai.model.objectadapter;

public class Circle implements Shape {
	private Shape adapter;
	public Circle() {
		this.adapter=new ObjectAdapter();
	}

	@Override
	public void draw() {
		adapter.draw();
	}

}
