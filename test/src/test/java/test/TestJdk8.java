/**
 * 
 */
package test;

import com.hujiang.basic.framework.core.util.DateUtil;
import com.hujiang.basic.framework.core.util.JsonUtil;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.training.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

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

	@Test
	public void testForEach_continue() {
		List<Integer> list = Lists.newArrayList(1,2,3,4,5,6,7,8,9);
		list.forEach(e -> {
			if (e == 2) {
				// 只是终止当前迭代，不会终止本次迭代 相当于continue,如果要break 则throw exception
				return;
			}

			System.out.println(e);
		});
	}

	@Test
	public void testSort1 () {
		List<Kid> kids = Lists.newArrayList(new Kid(1),new Kid(3),new Kid(5),new Kid(2));
		//kids.sort(Comparator.comparing());
	}

	@Test
	public void testSort2 () {
		List<Kid> kids = Lists.newArrayList(new Kid(1),new Kid(3),new Kid(5),new Kid(2));
		System.out.println("before sort: " + kids);
		kids.sort((o1, o2) ->
				Integer.compare(o1.getAge(), o2.getAge())
				//-Integer.compare(o1.getAge(), o2.getAge())
		);
		System.out.println("after sort: " + kids);
	}

	@Test
	public void testSort3 () {
		List<Kid> kids = Lists.newArrayList(new Kid(1),new Kid(3),new Kid(5),new Kid(2));
		System.out.println("before sort: " + kids);
		kids.sort(Comparator.comparingInt(Kid::getAge));
		System.out.println("after sort: " + kids);
		kids.sort(Comparator.comparingInt(Kid::getAge).reversed());
		System.out.println("after sort: " + kids);
	}

	@Test
	public void testSort4 () {
		List<Kid> kids = Lists.newArrayList(
				new Kid(DateUtil.toDateTime("2010-10-01 20:30:30")),
				new Kid(DateUtil.toDateTime("2009-10-01 20:30:00")),
				new Kid(DateUtil.toDateTime("2012-10-01 20:30:30")),
				new Kid(DateUtil.toDateTime("2009-10-01 20:29:59"))
		);
		System.out.println("before sort: " + kids);
		kids.sort(Comparator.comparing(Kid::getBirthday, (o1, o2) -> o1.compareTo(o2)));

		System.out.println("after sort: " + kids);
	}



	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Kid {
		private int age;
		private Date birthday;

		public Kid(int age) {
			this.age = age;
		}

		public Kid(Date birthday) {
			this.birthday = birthday;
		}

		public static Kid getKid(int age) {
			return  age < 18 ? new Kid(age, DateUtil.toDateTime("2000-10-15 21:00:00")) : null;
		}

		@Override
		public String toString() {
			return JsonUtil.object2JSON(this);
		}
	}

	@Test
	public void testOptional() {
		Kid kid = new Kid(20, new Date());

	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class AccessToken {
		private Date expireAt;

		public boolean hasExpired() {
			return expireAt.before(new Date());
		}

		public static AccessToken getAccessToken(String date, boolean create) {
			return create ? new AccessToken(DateUtil.toDateTime(date)) : null;
		}

		@Override
		public String toString() {
			return JsonUtil.object2JSON(this);
		}
	}
	@Test
	public void testOpt5() {
		AccessToken access = AccessToken.getAccessToken("2017-10-15 21:00:00", true);
		//AccessToken access = AccessToken.getAccessToken("2017-11-11 21:00:00", true);
		//AccessToken access = AccessToken.getAccessToken("2000-10-15 21:00:00", false);
		System.out.println(access);
		Optional.ofNullable(access).map(e -> {
			if (e.hasExpired()) {
				e = new AccessToken(new Date());
			}
			return e;
		}).ifPresent(e -> e.setExpireAt(DateUtil.toDateTime("2015-10-15 21:00:00")));

		System.out.println(access);
	}


	@Test
	public void testOpt4() {
		AccessToken access = AccessToken.getAccessToken("2000-10-15 21:00:00", true);
		//AccessToken access = AccessToken.getAccessToken("2017-11-11 21:00:00", true);
		//AccessToken access = AccessToken.getAccessToken("2000-10-15 21:00:00", false);
		System.out.println("-----------行不通----------");
		access = Optional.ofNullable(access).map(t -> {
			System.out.println("not expire");
			System.out.println(t);
			return t;
		}).map(t -> {
			System.out.println("expired, delete");
			AccessToken e = new AccessToken(new Date());
			System.out.println(e);
			return e;
		}).orElseGet(() -> {
			System.out.println("null");
			AccessToken t = new AccessToken(new Date());
			System.out.println(t);
			return t;
		});

		System.out.println(access);
	}

	@Test
	public void testOpt2() {
		Kid kid = Optional.ofNullable(Kid.getKid(20)).filter(e -> e.getAge() < 18).map(e -> {
			System.out.println(e.getAge());
			System.out.println(e.getBirthday());
			return e;
		}).orElseGet(() -> {
			Kid e = new Kid(18, new Date());
			return e;
		});

		System.out.println(kid);

	}

	@Test
	public void testOpt3() {
		Kid kid = Optional.ofNullable(Kid.getKid(10)).filter(e -> e.getAge() > 18).map(e -> {
			System.out.println(e.getAge());
			System.out.println(e.getBirthday());
			return e;
		}).orElseGet(() -> {
			Kid e = new Kid(18, new Date());
			return e;
		});

		System.out.println(kid);

	}

	@Test
	public void testStream2() {
		Set<Kid> kids = Sets.newHashSet();
		kids.stream().filter(kid -> kid.getBirthday().after(new Date()));
	}

	@Test
	public void testJoin() {
		Set<String> names = Sets.newHashSet("aa ", "bb", "  cc ", "   dd");
		String str = names.stream().map(StringUtils::trim).collect(Collectors.joining(","));
		System.out.println(str);

		str = names.stream().map(StringUtils::trim).collect(Collectors.joining(",", "start", "end"));
		System.out.println(str);
	}

	@Test
	public void testOptional2() {
		Kid kid = Kid.getKid(20);
		Kid kid2 = Optional.<Kid>empty().get();
		System.out.println(kid2);
	}

	@Test
	public void testOptional3() {
		List<Kid> kids = Lists.newArrayList(new Kid(10), new Kid(16), new Kid(18));
		Kid kid = kids.stream().filter(k -> k.getAge() > 20).findAny().map(k -> {
			System.out.println("find");
			return k;
		}).orElseGet(() -> {
			System.out.println("not find");
			return null;
		});

		System.out.println(kid == null);

		kid = kids.stream().filter(k -> k.getAge() > 20).findAny().map(k -> {
			System.out.println("find");
			return k;
		}).get();

		System.out.println(kid == null);
	}

	@Test
	public void testifelseif() {
		Kid kid1 = Kid.getKid(8);
		Kid kid2 = Kid.getKid(9);

		if (kid1 == null && kid2 == null) {
			System.out.println("kid1 null, kid2 null");
		} else if (kid1 != null && kid2 == null) {
			System.out.println("kid1 not null, kid2 null");
		} else if (kid1 == null && kid2 != null) {
			System.out.println("kid1 null, kid2 not null");
		} else {
			System.out.println("kid1 not null, kid2 not null");
		}
	}

	@Test
	public void testPeek() {
		List<String> list = Stream.of("one", "two", "three", "four")
				.filter(e -> e.length() >= 3)
				.peek(e -> System.out.println("Filtered value: " + e))
				.map(String::toUpperCase)
				.peek(e -> System.out.println("Mapped value: " + e))
				.collect(Collectors.toList());

		System.out.println(list);
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class UserInfo {
		Long userId;
		String userDomain;
	}

	@Test
	public void testList() {
		//List<UserInfo> list = new ArrayList();
		List<UserInfo> list = Lists.newArrayList(new UserInfo(1L, "cc"), new UserInfo(2L, "hj"));
		Long userId = list.stream().findAny().map(UserInfo::getUserId).orElse(100L);
		System.out.println(userId);

	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Employee {
		private Long id;
		private String name;
		private String city;
		private Integer sales;

		@Override
		public String toString() {
			return JsonUtil.object2JSON(this);
		}
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Person {
		private Long id;
		private String name;
		private String city;

		@Override
		public String toString() {
			return JsonUtil.object2JSON(this);
		}
	}

	public static String get(String str) {
		return str;
	}

	@Test
	public void testOptional10() {
		String a = get("a");
		System.out.println(Optional.ofNullable(a).orElse("x"));

		String b =get(null);
		System.out.println(Optional.ofNullable(b).orElse("y"));
	}

	@Test
	public void testList2Map() {
		List<Employee> list = Lists.newArrayList(
				new Employee(1L, "aa", "sh", 100),
				new Employee(1L, "bb", "sh", 200),
				new Employee(1L, "cc", "bj", 300),
				new Employee(1L, "dd", "hz", 400)
		);

		Map<String, Employee> map = list.stream().collect(Collectors.toMap(Employee::getName, v -> v));
		System.out.println(map);
	}

	@Test
	public void testOptional11() {
		Employee e1 = null;
		System.out.println(Optional.ofNullable(e1).map(e -> new Person(e.getId(), e.getName(), e.getCity())).orElse(null));

		Employee e2 = new Employee(1000L, "yk", "sh", 100);
		System.out.println(Optional.ofNullable(e2).map(e -> new Person(e.getId(), e.getName(), e.getCity())).orElse(null));
	}

	@Test
	public void testGroupingBy() {
		List<Employee> employees = Lists.newArrayList(
				new Employee(1L, "aa", "sh", 100),
				new Employee(1L, "bb", "sh", 200),
				new Employee(2L, "cc", "bj", 300),
				new Employee(2L, "dd", "hz", 400),
				new Employee(3L, "ee", "sh", 100),
				new Employee(3L, "ff", "sh", 200),
				new Employee(3L, "gg", "bj", 300),
				new Employee(4L, "hh", "hz", 400),
				new Employee(5L, "ii", "sh", 100),
				new Employee(5L, "jj", "sh", 200),
				new Employee(6L, "kk", "bj", 300),
				new Employee(6L, "ll", "hz", 400),
				new Employee(6L, "mm", "sh", 100),
				new Employee(6L, "nn", "sh", 200)
		);

		Map<Long, List<Employee>> map1 = employees.stream().collect(Collectors.groupingBy(Employee::getId));
		Map<Long, Set<Employee>> map2 =employees.stream().collect(Collectors.groupingBy(Employee::getId, Collectors.toSet()));
		System.out.println(map1);
		System.out.println(map2);
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Student {
		private String name;

		@Override
		public String toString() {
			return JsonUtil.object2JSON(this);
		}
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Teacher {
		private String name;
		private List<Student> students;

		@Override
		public String toString() {
			return JsonUtil.object2JSON(this);
		}
	}

	@Test
	public void test11() {
		Teacher teacher = new Teacher("xx", Lists.newArrayList(new Student("xx1"), new Student("xx2")));
		List<Student> students = Optional.ofNullable(teacher).map(Teacher::getStudents).orElse(null);
		System.out.println(students);

		teacher = null;
		students = Optional.ofNullable(teacher).map(Teacher::getStudents).orElse(null);
		System.out.println(students);

		teacher = new Teacher("xx",null);
		students = Optional.ofNullable(teacher).map(Teacher::getStudents).orElse(null);
		System.out.println(students);
	}

	public static class Context {
		public static void set(String name, String value) {
			System.out.println("name: " + name + ", value: " + value);
		}
	}

	@Test
	public void test12() {
		//Student obj = new Student("yk");
		//Student obj = null;
		Student obj = new Student("");
		Optional.ofNullable(obj).ifPresent(o -> Optional.ofNullable(o.getName()).filter(StringUtils::isNotBlank).ifPresent(e -> Context.set("name", e)));
	}


    @Test
    public void testStream3() {
        List<Integer> ids = Lists.newArrayList();
        for (int i = 0; i < 10000000; i++) {
            ids.add(i);
        }
        long start = System.currentTimeMillis();
        Map<Integer, String> dic = ids.stream().collect(Collectors.toMap(k -> k, v -> v.toString() + "^-^"));
        ids = ids.stream().filter(e -> e % 2 == 0).collect(Collectors.toList());

        List<String> names = ids.stream().map(dic::get).collect(Collectors.toList());
        long end = System.currentTimeMillis();
        System.out.println("costs: " + (end - start) + "ms");
    }

    @Test
    public void testStream4() {
        List<Integer> ids = Lists.newArrayList();
        for (int i = 0; i < 10000000; i++) {
            ids.add(i);
        }
        long start = System.currentTimeMillis();
        Map<Integer, String> dic = ids.parallelStream().collect(Collectors.toMap(k -> k, v -> v.toString() + "^-^"));
        ids = ids.stream().filter(e -> e % 2 == 0).collect(Collectors.toList());

        List<String> names = ids.parallelStream().map(dic::get).collect(Collectors.toList());
        long end = System.currentTimeMillis();
        System.out.println("costs: " + (end - start) + "ms");

    }

    @Data
	@NoArgsConstructor
	@AllArgsConstructor
    public static class UnionUser {
		private Long userId;
		private String userDomain;

		@Override
		public String toString() {
			return JsonUtil.object2JSON(this);
		}
	}

	@Test
	public void testFlatMap1() {
		Set<Long> ids = Sets.newHashSet(123456L, 234567L, 345678L);
		List<UnionUser> users = Stream.of(ids)
				.flatMap(e -> e.stream()).map(e -> new UnionUser(e, "hj"))
				.collect(Collectors.toList());

		System.out.println(users);
	}


	public static String B() {
		System.out.println("B()...");
		return "B";
	}

	@Test
	public  void testOrElseAndOrElseGet1() {
		System.out.println(Optional.of("A").orElse(B()));
	}

	/**
	 * If opt doesn't contain a value, the two are indeed equivalent. But if opt does contain a value, how many Foo objects will be created?

	 P.s.: of course in this example the difference probably wouldn't be measurable, but if you have to obtain your default value from a remote web service for example, or from a database, it suddenly becomes very important.
	 */
	@Test
	public  void testOrElseAndOrElseGet2() {
		System.out.println(Optional.of("A").orElseGet(() -> B()));
	}

	@Test
	public void testList_RemoveIf() {
		List<Long> all = Lists.newArrayList(1L,2L,3L,4L,5L,6L,7L,8L);
		//List<Long> ids1 = Lists.newArrayList(1L,2L,6L,7L,8L);
		List<Long> ids1 = Lists.newArrayList(1L,2L,3L,4L,5L,6L,7L,8L);
		List<Long> ids2 = Lists.newArrayList(all);
		//List<Long> ids2 = Lists.newArrayList();
		System.out.println(ids2);
		ids1.forEach(e-> ids2.removeIf(v -> Objects.equals(v, e)));
		System.out.println(ids2);
	}

	@Test
	public void testOptional_orElseThrow_1() {
		//boolean isBind = true;
		boolean isBind = false;
		String value = Optional.of(isBind).filter(e -> e).map(e -> "need bind").orElseThrow(NullPointerException::new);
		System.out.println(value);
	}

	@Test
	public void testOptional_empty() {

	}

	@Test
	public void testForEach() {
		List<Long> users = Lists.newArrayList();
		users.forEach(System.out::println);

	}

	@Test
	public void testGroupingBy1() {
		List<Integer> userIds = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);

		Map<Integer, List<Integer>> map1 = userIds.stream().collect(Collectors.groupingBy(e -> Utils.sharding(e, 12)));
		System.out.println(map1);

		Map<Integer, Long> map2 = userIds.stream().collect(Collectors.groupingBy(e -> Utils.sharding(e, 12), Collectors.counting()));
		System.out.println(map2);
	}

	@Test
	public void testGroupingBy2() {
		List<String> items = Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya");

		items.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}


	@Test
    public void testReduce() {
        List<Integer> nums = Lists.newArrayList(9, 1, 2, 3, 4, 5);


        Integer sum = nums.stream().reduce((x, y) -> {
            System.out.println("x: " + x);
            System.out.println("y: " + y);
            return  x + y;
        }).get();
        System.out.println(sum);
    }


    @Test
    public void testReduce2() {
        List<Integer> nums1 = Lists.newArrayList(1, 2, 3, 4, 5);
        List<Integer> nums2 = Lists.newArrayList(6,7);
        List<Integer> nums3 = Lists.newArrayList(8, 9,10);
        List<List<Integer>> list = Lists.newArrayList(nums1, nums2, nums3);

        List<Integer> nums = list.stream().reduce((x, y) -> {
            System.out.println("x: " + x);
            System.out.println("y: " + y);
            x.addAll(y);
            return x;
        }).get();
        System.out.println("nums: " + nums);

    }


    @Test
    public void testReduce3() {
        List<Integer> nums1 = Lists.newArrayList(1, 2, 3, 4, 5);
        List<Integer> nums2 = Lists.newArrayList(6,7);
        List<Integer> nums3 = null;
        List<Integer> nums4 = Lists.newArrayList(8, 9,10);
        List<List<Integer>> list = Lists.newArrayList(nums1, nums2, nums3, nums4);

        List<Integer> nums = list.stream().reduce(Lists.newArrayList(), (x, y) -> {
            System.out.println("x: " + x);
            System.out.println("y: " + y);
/*            if (CollectionUtils.isNotEmpty(y)) {
                x.addAll(y);
            }*/
            Optional.ofNullable(y).filter(CollectionUtils::isNotEmpty).ifPresent(x::addAll);
            return x;
        });
        System.out.println("nums: " + nums);

    }

    @Test
    public void testReduce4() {
        List<Integer> nums1 = Lists.newArrayList(1, 2, 3, 4, 5);
        List<Integer> nums2 = Lists.newArrayList(6,7);
        List<Integer> nums3 = null;
        List<Integer> nums4 = Lists.newArrayList(8, 9,10);
        List<List<Integer>> list = Lists.newArrayList(nums1, nums2, nums3, nums4);
        List<Integer> identity = Lists.newArrayList();
        List<Integer> nums = list.parallelStream().reduce(identity,
			(x, y) -> {
				System.out.println("accumulator x: " + x);
				System.out.println("accumulator y: " + y);
                System.out.println("accumulator: " + Thread.currentThread());
        	return Lists.newArrayList();
		}, (x, y) -> {
				System.out.println("combiner x: " + x);
				System.out.println("combiner y: " + y);
                System.out.println("combiner: " + Thread.currentThread());
			return Lists.newArrayList();
		});
        System.out.println("nums: " + nums);

    }

    public static class Service {

	}

	@Test
	public void testrReduce_asyn() {
		List<Integer> nums1 = Lists.newArrayList(1, 2, 3, 4, 5);
		List<Integer> nums2 = Lists.newArrayList(6,7);
		List<Integer> nums3 = null;
		List<Integer> nums4 = Lists.newArrayList(8, 9,10);
		List<List<Integer>> list = Lists.newArrayList(nums1, nums2, nums3, nums4);

		List<Integer> nums = list.stream().reduce(Lists.newArrayList(), (x, y) -> {
			System.out.println("x: " + x);
			System.out.println("y: " + y);
/*            if (CollectionUtils.isNotEmpty(y)) {
                x.addAll(y);
            }*/
			Optional.ofNullable(y).filter(CollectionUtils::isNotEmpty).ifPresent(x::addAll);
			return x;
		});
		System.out.println("nums: " + nums);

	}

    public static class MyTask {
        private final int duration;
        public MyTask(int duration) {
            this.duration = duration;
        }
        public int run() {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(duration * 1000);
            } catch (final InterruptedException e) {
                throw new RuntimeException(e);
            }
            return duration;
        }
    }

    @Test
    public void test_sync_parallelstream_completabefutrue() {
        List<MyTask> tasks = IntStream.range(0, 10)
            .mapToObj(i -> new MyTask(1))
            .collect(Collectors.toList());

        long start = System.currentTimeMillis();

        List<Integer> result = tasks.stream()
            .map(MyTask::run)
            .collect(Collectors.toList());
        long end = System.currentTimeMillis();
        System.out.println("同步开销: " + (end -start) + " , result: " + result);


        start = System.currentTimeMillis();

        List<Integer> result2 = tasks.parallelStream()
            .map(MyTask::run)
            .collect(Collectors.toList());
        end = System.currentTimeMillis();
        System.out.println("并行流开销: " + (end -start) + " , result: " + result2);

        start = System.currentTimeMillis();
        List<Integer> result3 = tasks.stream()
            .map(t -> CompletableFuture.supplyAsync(t::run))
            .collect(Collectors.toList()).stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList());
        end = System.currentTimeMillis();
        System.out.println("异步开销: " + (end -start) + " , result: " + result3);
    }


    @Test
	public void testIntSream() {
		IntStream.range(1, 10).forEach(System.out::println);

	}


	@Test
	public void testStream_AtomicInteger() {
		String[] names = {"Sam", "Pamela", "Dave", "Pascal", "Erik"};
		AtomicInteger index = new AtomicInteger();
		List<String> list = Arrays.stream(names)
			.filter(n -> n.length() <= index.incrementAndGet())
			.collect(Collectors.toList());

	}

	@Test
	public void test_range() {
		 Set<Integer> set = IntStream.range(1, 1).boxed().collect(Collectors.toSet());
		System.out.println(set);
	}

	@Test
	public void test_parallelStream() throws Exception {
		long firstNum = 1;
		long lastNum = 1_000_000;

		List<Long> list = LongStream.rangeClosed(firstNum, lastNum).boxed().collect(Collectors.toList());

		ForkJoinPool pool = new ForkJoinPool(4);
		long actualTotal = pool.submit(
			() -> list.parallelStream().reduce(0L, Long::sum)).get();

	}


	public static String C() {
		System.out.println("C()...");
		return "C";
	}

	@Test
	public void test_optional_orElse_orElseGet() {

		//System.out.println(Optional.of("A").orElse(C()));
		System.out.println(Optional.of("A").orElseGet(() -> C()));

	}

	@Test
	public void test_stream_max_min() {
		List<Integer> list = Lists.newArrayList(9,10,11,15,16);
		Integer min = list.stream().max((o1, o2) -> {
			if (o1 > o2) {
				return -1;
			} else if (Objects.equals(o1 , o2)) {
				return 0;
			} else {
				return 1;
			}
		}).orElse(null);

		Integer max = list.stream().max((o1, o2) -> {
			if (o1 > o2) {
				return 1;
			} else if (Objects.equals(o1 , o2)) {
				return 0;
			} else {
				return -1;
			}
		}).orElse(null);

		System.out.println(min);
		System.out.println(max);
	}


}
