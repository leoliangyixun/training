package com.yangkai.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorDemo {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		List user=new ArrayList();
		user.add("Ñî¿ª");
		user.add("¸¶´ä");
		user.add("³Âö¦");
		Iterator it=user.iterator();
		while(it.hasNext())
		{
			String name=(String) it.next();
			System.out.println(name);
		}
		System.out.println(user);
	}

}
