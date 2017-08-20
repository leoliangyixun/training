package com.yangkai.se;

import java.util.ArrayList;
import java.util.List;

import com.yangkai.vo.User;

public class StringArrayDemo {

	public static void main(String[] args) {
		//String[] s=new String[]{};
		//System.out.println(s.length);
		List<User> list=new ArrayList<User>();
		for(int i=0;i<10;i++){
			User user=new User();
			user.setName("name"+i);
			user.setSex('m');
			list.add(user);
		}
		User[] s=(User[]) list.toArray();
		//User[] s=list.toArray(new User[]{});
		System.out.println(s.length);
		for(int i=0;i<s.length;i++){
			System.out.println(s[i].getName()+"<--->"+s[i].getSex());
		}
	}

}
