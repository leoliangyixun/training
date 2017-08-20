package com.yangkai.model.objectadapter;

public class ObjectAdapter implements Shape{
	private NewShape newCircle1=new NewCircle();
	public ObjectAdapter() {
		
	}

	@Override
	public void draw() {
		newCircle1.newDraw();
	}

}
