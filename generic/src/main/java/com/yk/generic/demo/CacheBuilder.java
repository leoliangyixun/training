/**
 * 
 */
package com.yk.generic.demo;

/**
 * @author yangkai
 *
 */
public class CacheBuilder<K, V> {
	//静态变量不能有泛型
	private static CacheBuilder cacheBuilder;
	private LocalCache<K, V> localCache;
	
	public static <K, V> CacheBuilder<K, V> getInstance(){
		if (cacheBuilder == null){
			cacheBuilder = new CacheBuilder<>();
		}
		return cacheBuilder;
		
	}
	
	public LocalCache<K, V> getLocalCache() {
		
		if (localCache == null) {
			localCache = new LocalCache<K, V>();
		}
		return localCache;
	}
	


}
