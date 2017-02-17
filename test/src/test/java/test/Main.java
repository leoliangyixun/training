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
	
	@Test
	public void test() {
		Long a = null;
		System.out.println(a == 0l);
		System.out.println(a == 0);
	}

}
