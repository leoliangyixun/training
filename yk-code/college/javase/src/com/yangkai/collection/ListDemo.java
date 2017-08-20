package com.yangkai.collection;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {

	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		System.out.println(list.isEmpty());
		System.out.println(list.size());

		List l=new ArrayList();
		Integer i=new Integer(1);
		Boolean b=new Boolean(false);
		String s=new String("hello");
		l.add(i);
		l.add(b);
		l.add(s);
		for(int j=0;j<l.size();j++){
			System.out.println(l.get(j));
		}
	}

}
