package com.yangkai.test;

import java.util.HashSet;
import java.util.Set;

import com.yangkai.test.domain.Point;

public class SetTest {
	private String name;
	private int age;
	public SetTest(String name) {
		this.name=name;
	}
	public void setAge(int age){
		this.age=age;
	}

	public static void main(String[] args) {
//		Set<SetTest> user=new HashSet<SetTest>();
//		SetTest t1=new SetTest("yangkai");
//		SetTest t2=new SetTest("yangkai");
//		t2.setAge(24);
//		user.add(t1);
//		user.add(t2);
//		System.out.println(t1.hashCode());
//		System.out.println(t2.hashCode());
//		System.out.println(t2.equals(t2));
//		System.out.println(user);
//		
//		Set<String> set=new HashSet<String>();
//		String s1=new String("yangkai");
//		String s2=new String("yangkai");
//		System.out.println(s1);
//		System.out.println(s2);
//		System.out.println(s1.hashCode());
//		System.out.println(s2.hashCode());
//		set.add(s1);
//		set.add(s2);
//		System.out.println(set);
		
		Set<Point> set=new HashSet<Point>();
/*		Point p1=new Point();
		Point p2=new Point();
		Point p3=new Point();*/
		Point p1=new Point(1,1,1);
		Point p2=new Point(1,2,1);
		Point p3=new Point(1,1,1);
		set.add(p1);
		set.add(p2);
		set.add(p3);
//	    System.out.println(p1.hashCode()+":"+p2.hashCode()+":"+p3.hashCode());
		System.out.println(set);
		
		p1.x=3;
		/*
		 * �޸�p1�����xֵ֮��p1����Ĺ�ϣֵ�ı��ˣ���p1�����λ�ò�û�иı䣬
		 * ������remove()����ɾ��p1�����ʹ���µĹ�ϣֵȥ��������󣬵�Ȼ�ͻ��Ҳ����ö�������remove�����᷵��false��
		 * p1����Ҳ���ᱻɾ����
		 * */
		boolean bool=set.remove(p1);
		System.out.println(bool);
		System.out.println(set);
	}

}
