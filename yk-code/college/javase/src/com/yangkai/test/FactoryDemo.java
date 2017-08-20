package com.yangkai.test;
class Teacher{
	String name=null;
	public void setName(String name)
	{
		this.name=name;
	}
	public String getName()
	{
		return name;
	}
}
class Student{
	String name=null;
	public void setName(String name)
	{
		this.name=name;
	}
	public String getName()
	{
		return name;
	}
}

class User1{
	Teacher t=null;
	Student s=null;
	public  static User1 u=new User1();
	private User1(){
		 t=new Teacher();
		 s=new Student();
	}
	
	public  Teacher getTeacher()
	{
		return t;
	}
	public  Student getStudent()
	{	
		return s;
	}
	public static User1 getInstance()
	{
		return u;
	}
}
class User2{
	 Teacher t=null;
	 Student s=null;
	private User2(){
			 
	}	
	public  static Teacher getTeacher()
	{
		return new Teacher();	
	}
	public  static Student getStudent()
	{
		return new Student();	
	}
}
class User3{
	//static Teacher t=new Teacher();
	//static Student s=new Student();
	static Teacher t=null;
	static Student s=null;
	public User3(){//只有构造User3对象时才会生成Teacher，Student对象。
		 t=new Teacher();
		 s=new Student();	 
	}	
	public  static Teacher getTeacher()
	{
		return t;	
	}
	public  static Student getStudent()
	{
		return s;	
	}
}
class FactoryDemo {
	public static void main(String[] args) 
	{ 
		/*
		Student s=User.getInstance().getStudent();
		s.setName("bitch");
		String sname=s.getName();
		System.out.println(sname);
		
		Teacher t=User.getTeacher();
		t.setName("fuck");
		String tname=t.getName();
		System.out.println(tname);
		*/
		Teacher t1=User2.getTeacher();
		Teacher t2=User2.getTeacher();
		Teacher t3=User2.getTeacher();
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t3);
		//s1,s2,s3指向3个不同对象
		Student s1=User1.getInstance().getStudent();
		Student s2=User1.getInstance().getStudent();
		Student s3=User1.getInstance().getStudent();
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		//s1,s2,s3指向同一个对象(单例模式)
		Student s11=new User3().getStudent();
		Student s12=new User3().getStudent();
		Student s13=new User3().getStudent();
		System.out.println(s11);
		System.out.println(s12);
		System.out.println(s13);
		
		
	}

}