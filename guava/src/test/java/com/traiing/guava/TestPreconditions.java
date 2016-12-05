/**
 * 
 */
package com.traiing.guava;

import org.junit.Test;

import com.google.common.base.Preconditions;

/**
 * @author yangkai
 *
 */
public class TestPreconditions {
	@Test
	public void test() {
		Preconditions.checkNotNull(null, "hello:%s, please call %s to get your %s", "yangkai", "13166016298", "books");
		String str = "hello:%s, please call %s to get your %s";
	System.out.println(String.format(str, "yangkai", "13166016298", "books"));
	
	}

}
