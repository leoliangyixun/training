package com.yangkai.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapDemo {

	public static void main(String[] args) {
		
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("�", 25);
		map.put("����", 23);
		map.put("����", 20);
		map.put("����", 24);
		map.put("����", 22);
		/*
		for(String key:map.keySet())
		{
			System.out.println(key+"---->"+map.get(key));
		}
		System.out.println("--------------------------");
		for(Entry<String, Integer> set:map.entrySet())
		{
			System.out.println(set.getKey()+"---->"+set.getValue());
		}
		System.out.println("--------------------------");
		System.out.println(map.containsKey("�"));
		System.out.println("--------------------------");
		System.out.println(map.get("�"));
		*/
		System.out.println(map.get("leo"));
	}

}
