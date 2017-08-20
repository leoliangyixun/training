package com.yangkai.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapIterator {

	public MapIterator() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Map<String, String> map=new HashMap<String,String>();
		map.put("key1","yangkai");
		map.put("key2","fucui");
		map.put("key3","lavender");
		Set<Entry<String,String>> entrys=map.entrySet();
		for(Entry<String, String> entry:entrys){
			System.out.println(entry.getKey()+"<------>"+entry.getValue());
		}
		System.out.println("---------------------------------------------");
		Set<String> keys=map.keySet();
		for(String key:keys){
			System.out.println(key);
		}
		System.out.println("---------------------------------------------");
		Iterator<String> it=keys.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
}
interface MyMap<K, V>{
	 boolean isEmpty();
	interface Entry<K, V>{
		 K getKey();
	}
}
class MyHashMap<K, V> implements MyMap<K, V>{

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	static class Entry<K, V> implements MyMap.Entry<K, V>{

		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}