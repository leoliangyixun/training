package com.yangkai.model.classadapter;

public class Circle implements Shape {
	private Shape adapter;
	public Circle() {
		this.adapter=new ClassAdapter();
	}

	@Override
	public void draw() {
		adapter.draw();
	}

}
