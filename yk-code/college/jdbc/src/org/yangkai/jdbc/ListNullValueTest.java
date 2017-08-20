package org.yangkai.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.yangkai.jdbc.domain.User;

public class ListNullValueTest {

	public static void main(String[] args) {
		List<User> t=new ArrayList<User>();
		System.out.println(t==null);
		System.out.println(t.size());

	}

}
