package test;

import org.junit.Test;

public class Main {
	public Main() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
/*		int a = 0; //a++;
		++a;
		System.out.println(a);*/
	    
	    Boolean bool = null;
	    if(bool) {
	        System.out.println("true" + bool);
	    } else {
	        System.out.println("false" + bool);
	    }
	}


	@Test

	public void testMuitlDeclare() {
		User u1,u2 = new User("alisa",27);
		System.out.println(u1);
		System.out.println(u2);
	}


	public static class User {
		private String name;
		private Integer age;

		public User(String name, Integer age) {
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}
	}

}
