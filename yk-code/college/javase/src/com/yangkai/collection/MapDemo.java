package com.yangkai.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapDemo {

	public static void main(String[] args) {
		
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("Ñî¿ª", 25);
		map.put("ÑîÔÆ", 23);
		map.put("ÑîÇé", 20);
		map.put("¸¶´ä", 24);
		map.put("¸¶æÃ", 22);
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
		System.out.println(map.containsKey("Ñî¿ª"));
		System.out.println("--------------------------");
		System.out.println(map.get("Ñî¿ª"));
		*/
		System.out.println(map.get("leo"));
	}

}
