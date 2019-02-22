package org.yangkai.jdbc;

import java.lang.reflect.Field;

import org.yangkai.jdbc.domain.Account;
import org.yangkai.jdbc.domain.User;

 abstract class GenericTest {

	public  abstract <T> T genericMethod(int id);



}
 public class GenericMethodTest extends GenericTest{
		public static void main(String[] args) {
			GenericMethodTest test=new GenericMethodTest();
			
			User user=test.genericMethod(0);
			Account account=test.genericMethod(1);
		}

		public  <T> T genericMethod(int id) {
			
			return null;
		}
 }
