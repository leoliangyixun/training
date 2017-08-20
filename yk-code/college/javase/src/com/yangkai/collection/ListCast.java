package com.yangkai.collection;

import java.util.ArrayList;
import java.util.List;

public class ListCast {


	public static void main(String[] args) {
		List<String> a=new ArrayList<String>();
		a.add("Ñî¿ª");
		//a.add("ÑîÔÆ");
		//a.add("ÑîÇé");
		String c=a.toString();
		System.out.println(c);
		String[] b= a.toArray(new String[0]);
		for(int i=0;i<b.length;i++)
		{
			System.out.println(b[i]);
		}
		
		

	}

}
