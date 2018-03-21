/**
 * 
 */
package com.training.jdk.generic;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;

/**
 * @author yangkai
 *
 */
public class TestGeneric {
	
	@Test
	public void test() {
		ImmutableMap<String, String> map = ImmutableMap.<String, String>builder().put("name", "yk").build();
	}

}
