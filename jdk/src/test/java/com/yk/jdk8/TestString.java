/**
 * 
 */
package com.yk.jdk8;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * @author yangkai
 *
 */
public class TestString {
	
    @Test
    public void test() {
    	
    	System.out.println(Lists.newArrayList(StringUtils.split(" 1,23,,4 ,6  ,  8", ",")).stream().map(e -> Integer.parseInt(e.trim())).collect(Collectors.toSet()));
    	System.out.println(Lists.newArrayList(StringUtils.split(" 1,23,,4 ,6  ,  8", ",")).stream().map(e -> Integer.parseInt(e.trim())).collect(Collectors.toList()));
    }

    @Test
    public void test2() {
    	
  }
}
