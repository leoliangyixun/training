package com.yangkai.reflect;

import com.yangkai.vo.Student;
import com.yangkai.vo.User;

public class ClassTypeDemo<T> {
	private Class<T> classType;
	public ClassTypeDemo() {
	}
	public void test(Class<T> classType){
		this.classType=classType;
	}
	public T getInstance() throws Exception{
		return classType.newInstance();
	}
	public static void main(String[] args)throws Exception {
		ClassTypeDemo<User> demo1=new ClassTypeDemo<User>();
		demo1.test(User.class);
		User user=demo1.getInstance();
		user.setName("Ñî¿ª");
		user.setSex('ÄÐ');
		System.out.println(user.getName()+"<------>"+user.getSex());
		System.out.println("------------------------------------------");
		ClassTypeDemo<Student> demo2=new ClassTypeDemo<Student>();
		demo2.test(Student.class);
		Student student=demo2.getInstance();
		student.setId(2009);
		student.setName("Ñî¿ª");
		student.setSex('ÄÐ');
		System.out.println(student.getId()+"<------>"+student.getName()+"<------>"+student.getSex());
	}
}
/*
class User{
	private String name;
	private String sex;
	public String getName() {
		return name;
	}	
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
*/
/*
class Student{
	private Integer id;
	private String name;
	private String sex;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}	
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
*/