/**
 * 
 */
package test;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.hujiang.basic.framework.core.util.DateUtil;
import javafx.beans.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jooq.util.maven.example.postgres.Public;
import org.junit.Test;
import org.springframework.context.support.StaticApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.val;

/**
 * @author yangkai
 *
 */
public class TestJdk8 {
	
	@Test
	public void test() {
		List<User> users = Lists.newArrayList(new User(1, "yk", 20), new User(2, "gwj", 18));
		System.out.println(users.stream().collect(Collectors.toMap(e -> "name", e -> e)));
	}
	
	@Test
	public void test2() {
		String str = "1  ,2 ,3,   4";
		Set<String> ss = Sets.newHashSet(StringUtils.split(str, ","));
		ss.stream().peek(null);
		System.out.println(ss);
		ss.forEach(e -> e.trim());
		System.out.println(ss);
		System.out.println(Sets.newHashSet(StringUtils.split("1  ,2 , 3,   4", ",")).stream().map(e -> e.trim()).collect(Collectors.toSet()));
	}
	@Test
	public void test3() {
		
		List<User> users = Lists.newArrayList(new User(1, "111", 1), new User(2, "222", 2), new User(3, "333", 3));
		
		users.stream().forEach(e -> e.setName(e.getName() + "append"));
		//System.out.println(users);
		users.stream().forEach(e -> System.out.println(e + "-->"));
		
		
	}
	
	@Test
	public void test4() {
		List<String> ss = Lists.newArrayList("yy");
		for (String s : ss) {
			System.out.println(StringUtils.isBlank(s));
		}
		System.out.println("------------------------");
		System.out.println(ss);
		System.out.println("anyMatch: " + ss.stream().anyMatch(p -> Objects.equals(p, "")));
		System.out.println("allMatch: " + ss.stream().allMatch(p -> Objects.equals(p, "xx")));
	}
	
	@Test
	public void test5() {
		List<Integer> nums = Lists.newArrayList(1,1,null,2,3,4,null,5,6,7,8,9,10);
		
		System.out.println(nums.stream().filter(num -> num != null).
		            distinct().
		            peek(System.out::println).collect(Collectors.toList()));
	}
	
	@Test
	public void test6() {
		Set<String> ids = Sets.newHashSet("1","2","3","4","5","6","7","8","9");
		System.out.println(ids);
	//	ids = ids.stream().limit(2).collect(Collectors.toCollection(HashSet :: new));
		//ids = ids.stream().limit(2).collect(Collectors.toCollection(() -> new HashSet<>()));
		//ids = ids.stream().limit(2).collect(Collectors.toSet());
		//System.out.println(ids);
		//Set<Integer> subset = ImmutableSet.copyOf(Iterables.limit(set, 20));
		
		ids = ids.stream().skip(5).limit(ids.size() - 2).collect(Collectors.toSet());
		System.out.println(ids);
		System.out.println("------------------------------------------------");
		
		System.out.println(500 % 200 == 0 ? 500 / 200 : (500 / 200) + 1);
	}
	
	@Test
	public void test7() {
		User u1 = new User(1, "a", 1);
		User u2 = new User(2, "b", 3);
		User u3 = new User(3, "c", 2);
		User u4 = new User(4, "d", 5);
		User u5 = new User(5, "e", 4);
		User u6 = new User(6, "f", 4);
		List<User> users = Lists.newArrayList(u1, u2, u3, u4, u5, u6);
		System.out.println("origin: " + users);
		
		users = users.stream().sorted((o1, o2) -> {
            if (o1.getAge() < o2.getAge()) {
                return 1;
            } else  if (o1.getAge() == o2.getAge()) {
                return 0;
            } else {
                return -1;
            }
        }).collect(Collectors.toList());
		System.out.println("sort1: " + users);
		
		users = Lists.newArrayList(u1, u2, u3, u4, u5, u6);
		users = users.stream().sorted((o1, o2) -> {
            if (o1.getAge() < o2.getAge()) {
                return -1;
            } else  if (o1.getAge() == o2.getAge()) {
                return 0;
            } else {
                return 1;
            }
        }).collect(Collectors.toList());
		System.out.println("sort2: " + users);
		
		users = Lists.newArrayList(u1, u2, u3, u4, u5, u6);
		Collections.sort(users, (o1, o2) -> {
            if (o1.getAge() < o2.getAge()) {
                return -1;
            } else  if (o1.getAge() == o2.getAge()) {
                return 0;
            } else {
                return 1;
            }
        });
		System.out.println("sort3: " + users);
		
	}
	
	@Test
	public void test8() {
		String a = "a";
		String b = "b";

		List<String> users = Lists.newArrayList(a, b);
		System.out.println(users.size());
		users.addAll(Arrays.asList(new String[]{}));
		//users.addAll(new ArrayList<String>());
		System.out.println(users);
		System.out.println(users.size());
	}
	
	
	@Test
	public void test9() {
		User user = null;
		//User user = new User(2, "yk", 20);
		Optional<User> opt = Optional.ofNullable(user);
		User u = opt.orElseGet(() -> {
			if (opt.isPresent()) {
				System.out.println("***");
				throw new RuntimeException("xx");
			} else {
				return new User(1, "gwj", 18);
			}
		});
		System.out.println(u);
		
	}

	@Test
	public void test10() {
		Optional.ofNullable(new User(1,"xxx", 20)).ifPresent(t -> System.out.println(t.getName()));
		Optional<User> userOpt = Optional.ofNullable(null);
		userOpt.ifPresent(t -> System.out.println(t.getName()));

		System.out.println("end");
	}

	
	@Data
	public static class User {
		private Integer id;
		private String name;
		private Integer age;
		private String area;
		private Date birthday;
		private boolean isDefault;

		public User() {
		}
		
		public User(Integer id, String name, Integer age) {
			this.id = id;
			this.name = name;
			this.age = age;

		}

		public User(Integer id, String name, Integer age, String area, boolean isDefault, Date birthday) {
			this.id = id;
			this.name = name;
			this.age = age;
			this.area = area;
			this.isDefault = isDefault;
			this.birthday = birthday;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
		}
		
	}

	@Test
	public void testFunc_1() {
		Set<User> users = Sets.newHashSet(
				new User(1,"xxx", 10, "sh", true, DateUtil.toDate("1988-09-05")),
				new User(2,"yyy", 15,"sh", true,DateUtil.toDate("1988-09-05")),
				new User(3,"zzz", 20,"sh",false, DateUtil.toDate("1988-09-05"))
		);

		users = users.stream().filter(e -> e.getAge() > 18).collect(Collectors.toSet());

		System.out.println(users);

		users.stream().map((e) -> e.getName());

	}

	@Test
	public void testFunc_2() {
		List<User> users = Lists.newArrayList(
				new User(1,"xxx", 10, "sh", true, DateUtil.toDate("1988-09-05")),
				new User(2,"yyy", 15,"sh", true, DateUtil.toDate("1990-09-05")),
				new User(3,"zzz", 20,"sh", true, DateUtil.toDate("1991-09-05")),
				new User(1,"mmmm", 10, "wh", true, DateUtil.toDate("1992-09-05")),
				new User(2,"nnn", 15,"bj", true, DateUtil.toDate("1985-09-05")),
				new User(3,"sss", 20,"bj", true, DateUtil.toDate("1993-09-05"))
		);

	/*	users = users.stream().filter(e -> e.getAge() > 18).collect(Collectors.toSet());

		System.out.println(users);*/



		Map<String, List<User>> map1 = users.stream().collect(Collectors.groupingBy(User::getArea));
		Map<String, List<User>> map2 = users.stream().collect(Collectors.groupingBy(User::getArea, Collectors.toList()));
		System.out.println(map1);
		System.out.println(map2);

	}

	@Test
	public void testOrderBy() {
		List<User> users = Lists.newArrayList(
				new User(1,"xxx", 10, "sh", true, DateUtil.toDate("1988-09-05")),
				new User(2,"yyy", 15,"sh", true, DateUtil.toDate("1990-09-05")),
				new User(3,"zzz", 20,"sh", true, DateUtil.toDate("1991-09-05")),
				new User(1,"mmmm", 10, "wh", false,DateUtil.toDate("1992-09-05")),
				new User(2,"nnn", 15,"bj", false, DateUtil.toDate("1985-09-05")),
				new User(3,"sss", 20,"bj", true, DateUtil.toDate("1993-09-05"))
		);
		//System.out.println(users);
		//---------------------------------------------------------------
//		users.sort((o1, o2) -> {
//			int compare = Boolean.compare(o1.isDefault(), o2.isDefault());
//			if (compare == 0) {
//				int compare2 = o1.getBirthday().compareTo(o2.getBirthday());
//			}
//
//			return compare;
//		});

		//System.out.println(users);
		//---------------------------------------------------------------
//		users.sort((o1, o2) -> -new CompareToBuilder().append(o1.isDefault(), o2.isDefault()).append(o1.getBirthday(), o2.getBirthday()).toComparison());
//		System.out.println(users);
		//---------------------------------------------------------------

		Comparator<User> c1 = Comparator.comparing((e) -> e.isDefault(), (o1, o2) -> - Boolean.compare(o1, o2));
		Comparator<User> c2 = c1.thenComparing((e) -> e.getBirthday(), (o1, o2) -> - o1.compareTo(o2));
		//users.sort(c2);
		users.sort(Comparator.<User, Boolean>comparing((e) -> e.isDefault(), (o1, o2) -> - Boolean.compare(o1, o2)).thenComparing((e) -> e.getBirthday(), (o1, o2) -> - o1.compareTo(o2)));
		System.out.println(users);

		//---------------------------------------------------------------

	}

	@Test
	public void testStream() {
		List<User> users = Lists.newArrayList(
				new User(1,"xxx", 10, "sh", true, DateUtil.toDate("1988-09-05")),
				new User(2,"yyy", 15,"sh", true, DateUtil.toDate("1990-09-05")),
				new User(3,"zzz", 20,"sh", true, DateUtil.toDate("1991-09-05")),
				new User(4,"mmm", 10, "wh", false,DateUtil.toDate("1992-09-05")),
				new User(5,"nnn", 15,"bj", false, DateUtil.toDate("1985-09-05")),
				new User(6,"sss", 20,"bj", true, DateUtil.toDate("1993-09-05"))
		);
		User u = users.stream().filter((r) -> r.getId() == 10).findFirst().orElseGet(() -> users.stream().filter((r) -> !r.isDefault()).findFirst().orElse(null));
		System.out.println(u);

	}

	@Test
	public void testOrderBy2() {
		List<User> users = Lists.newArrayList(
				new User(1,"xxx", 10, "sh", true, DateUtil.toDate("1988-09-05")),
				new User(2,"yyy", 15,"sh", true, DateUtil.toDate("1990-09-05")),
				new User(3,"zzz", 20,"sh", true, DateUtil.toDate("1991-09-05")),
				new User(1,"mmm", 10, "wh", false,DateUtil.toDate("1992-09-05")),
				new User(2,"nnn", 15,"bj", false, DateUtil.toDate("1985-09-05")),
				new User(3,"sss", 20,"bj", true, DateUtil.toDate("1993-09-05"))
		);

		users.sort(Comparator.<User, Boolean>comparing((e) -> e.isDefault(), (o1, o2) -> - Boolean.compare(o1, o2)).thenComparing((e) -> e.getBirthday(), (o1, o2) -> - o1.compareTo(o2)));
		System.out.println(users);
	}

	@Test
	public void limit() {
		List<Integer> ids = null;
		ids = Lists.newArrayList(1,2,3,4,5,6,7,8,9).stream().limit(2).collect(Collectors.toList());
		System.out.println(ids);
		ids = Lists.newArrayList(1,2,3,4,5,6,7,8,9).stream().skip(2).limit(2).collect(Collectors.toList());
		System.out.println(ids);
	}

	@Test
	public void limit2() {
		int offset = 10;
		Set<Integer> id = Sets.newHashSet(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);
		Set<Integer> id1 = id.stream().limit(offset).collect(Collectors.toSet());
		Set<Integer> id2 = id.stream().skip(offset).limit(id.size() - offset).collect(Collectors.toSet());
		System.out.println(id);
		System.out.println(id1);
		System.out.println(id2);
        System.out.println("------------------------------------------------------------------------------------------");
		List<Integer> age = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);
		Set<Integer> age1 = age.stream().limit(offset).collect(Collectors.toSet());
		Set<Integer> age2 = age.stream().skip(offset).limit(age.size() - offset).collect(Collectors.toSet());
		System.out.println(age);
		System.out.println(age1);
		System.out.println(age2);
        System.out.println("------------------------------------------------------------------------------------------");
        List<Integer> hourse = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);
        List<Integer> hourse1 = hourse.stream().limit(offset).collect(Collectors.toList());
        List<Integer> hourse2 = hourse.stream().skip(offset).limit(hourse.size() - offset).collect(Collectors.toList());
        System.out.println(hourse);
        System.out.println(hourse1);
        System.out.println(hourse2);
        System.out.println("------------------------------------------------------------------------------------------");
	}

    @Test
    public void optional() {
	    //User user = null;
        User user = new User(1,"yk", 20);
        Optional.ofNullable(user).ifPresent(c -> {
            System.out.println(c.getName());
            System.out.println(c.getAge());
        });
    }

    @Test
	public void optional2() {
		//List<User> users = null;
		//List<User> users = Lists.newArrayList(new User(1, "yk", 28), new User(2, "gwj", 27));
		//System.out.println(Optional.ofNullable(users).isPresent());
		//Optional.ofNullable(users).ifPresent(e -> e.forEach(x -> System.out.println(x.getName())));
		//Optional<List<User>> opt = Optional.ofNullable(users);
		//opt.isPresent()
		//Optional.ofNullable(users).filter(e -> e.size() > 0);
	}

	@Test
	public void optional3() {
		List<User> users = Lists.newArrayList(null, new User(2, "gwj", 27));
		Optional.ofNullable(users).ifPresent(e -> e.forEach(x -> System.out.println(x.getName())));
		//exception

	}

	@Test
	public void optional4() {
		List<User> users = Lists.newArrayList();
		Optional.ofNullable(users).ifPresent(e -> e.forEach(x -> System.out.println(x.getName())));
		//right
	}

	@Test
	public void foreach() {
		String ids = "123456,456789,   345678    ,456789    ,   123890,";
		System.out.println(ids);
		Set<String> userIds = Arrays.stream(StringUtils.split(ids, ",")).map(e -> StringUtils.trim(e)).collect(Collectors.toSet());
		System.out.println(userIds);
		userIds.forEach(e -> System.out.println(e));
		System.out.println(StringUtils.join(userIds, ","));
	}

	@Test
	public void foreach2() {
		String ids = "63648834， 68481812";
		System.out.println(ids);
		Set<String> userIds = Arrays.stream(StringUtils.split(ids, ",")).map(e -> StringUtils.trim(e)).collect(Collectors.toSet());
		System.out.println(userIds);
		userIds.forEach(e -> System.out.println(e));
		System.out.println(StringUtils.join(userIds, ","));
	}

	@Test
	public void testMap() {
		String s = "a,b  , ,,,,,,, c,d,  e  ,f,  g";
		System.out.println(StringUtils.split(s, ","));
		Set<String> ss = Arrays.stream(StringUtils.split(s, ",")).map(e -> e.trim()).collect(Collectors.toSet());
		System.out.println(ss.size() + ":" + ss);
		Set<String> ss2 = Arrays.stream(StringUtils.split(s, ",")).collect(Collectors.toSet());
		System.out.println(ss2.size() + ":" + ss2);
	}

	@Test
	public void testMap2() {
		String s = "a,b  , ,,,,,,, c,d,  e  ,f,  g";
		Set<String> ss = Arrays.stream(	StringUtils.stripAll(StringUtils.split(s, ","))).collect(Collectors.toSet());
		System.out.println(ss.size() + ":" + ss);
		Set<String> ss2 = Arrays.stream(StringUtils.split(s, ",")).collect(Collectors.toSet());
		System.out.println(ss2.size() + ":" + ss2);
	}

	@Test
	public void testMap3() {
		String s = "a,b  , ,,,,,,, c,d,  e  ,f,  g";
		Set<String> ss = Arrays.stream(	StringUtils.stripAll(StringUtils.split(s, ","))).collect(Collectors.toSet());
		System.out.println(ss.size() + ":" + ss);
		Set<String> ss1 = Arrays.stream(StringUtils.split(s, ",")).map(String::trim).collect(Collectors.toSet());
		System.out.println(ss1.size() + ":" + ss1);
		Set<String> ss2 = Arrays.stream(StringUtils.split(s, ",")).collect(Collectors.toSet());
		System.out.println(ss2.size() + ":" + ss2);
	}


	   
}
