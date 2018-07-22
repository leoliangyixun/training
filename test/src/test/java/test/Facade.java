package test;

import java.util.Map;
import java.util.Objects;

import com.google.common.collect.ImmutableMap;
import com.hujiang.basic.framework.core.util.JsonUtil;
import com.test.yk.aop.OpLogger;
import lombok.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

public class Facade {
	public Facade() {
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
	public void test5() {
		Employee e = new Employee("yk", 20, "yangkai@hujang.com", "shanghai");
		Service service = new Service();
		service.exec(e);
	}

	@org.springframework.stereotype.Service
	public static class Service {
		@OpLogger("exec")
		public void exec(Object obj) {
			Person p = (Person)obj;
			System.out.println(p.getName() + " : " + p.getAge());
		}
	}

    @NoArgsConstructor
	public static abstract class Person {
        @Setter
        @Getter
		protected String name;
        @Setter
        @Getter
		protected Integer age;

		public Person(String name, Integer age) {
			this.name = name;
			this.age = age;
		}

        public String clearSensitive() {
            //Person obj = JsonUtil.json2Object(JsonUtil.object2JSON(this), Person.class);
            Person obj = JsonUtil.deserialize(JsonUtil.serialize(this));
            return obj.toString();
        }

	    @Override
	    public String toString() {
	        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	    }
	}


    @NoArgsConstructor

	public static class Employee extends Person {
	    @Setter
        @Getter
		private String mail;
        @Setter
        @Getter
		private String address;

		public Employee(String name, Integer age) {
			super(name, age);
		}

		public Employee(String name, Integer age, String mail, String address) {
			super(name, age);
			this.mail = mail;
			this.address = address;
		}
		
/*	    @Override
	    public String toString() {
	        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	    }*/

	}


	@Test
	public void test4() {
		Employee e = new Employee("yk", 20, "leoliangyixun@163.com", "shanghai");
		System.out.println(e.clearSensitive());

	}

	@Test
	public void test() {
		Long a = null;
		System.out.println(a == 0l);
		System.out.println(a == 0);
	}
	
	@Test
	public void test2() {
		Employee user = new Employee("yk", 20);
		System.out.println(Objects.equals(user.getAge(), 20));//true
		System.out.println(user.getAge() == 20);//true
		System.out.println(user.getAge() == new Integer(20));//false
	}


	@Test
	public void test3() {
		Facade facade = new Facade();
		facade.show();
		facade.show2();
		facade.show3();
	}

	public void show() {
		System.out.println("show");
	}
	public void show2() {
		System.out.println("show2");
		return;
	}
	public void show3() {
		System.out.println("show3");
	}

	@Test
	public  void testAOP() {
		Employee e = new Employee("yk", 20, "yangkai@hujang.com", "shanghai");
		Service service = new Service();
		service.exec(e);
	}
	
	@Test
	public void test_hj() {
		Map<String, String> map = ImmutableMap.of("batchId", "123456789");
		System.out.println(JsonUtil.object2JSON(map));
	}
	
	@Test
	public void test_null() {
		Object a = null;
		Integer obj = (Integer) a;
		System.out.println(obj);

	}
	
	@Test
	public void serialize() {
	    Employee e1 = new Employee("yk", 20, "yangkai@hujiang.com", "shanghai");
	    //Employee e2 = JsonUtil.deserialize(JsonUtil.serialize(e1));

		Employee e2 = JsonUtil.json2Object(JsonUtil.object2JSON(e1), Employee.class);
	    System.out.println(e2);
	}


}
