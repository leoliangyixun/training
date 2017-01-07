/**
 * 
 */
package test;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

/**
 * @author yangkai
 *
 */
public class TestString {
	
    @Test
    public void testStringIndexOf() {
        String s = "  {\"name\": \"yangkai\",\"colleage\":{\"name\": \"wuhan\"}   }     ";
        System.out.println(s + "xx");
        System.out.println(s.indexOf("{"));
        System.out.println(s.contains("{"));
        System.out.println(s.trim() + "xx");
        System.out.println(s.trim());
        System.out.println(s.trim().indexOf("{"));
        System.out.println(s.trim().length());
        System.out.println(s.trim().lastIndexOf("}"));
        System.out.println(s.trim().indexOf("{") == 0 && s.trim().lastIndexOf("}") == s.trim().length()-1);
        
    }
    
    @Test
    public void testSplit() {
        String channelIds = "1,2,3,a";
        String[] ids = StringUtils.split(channelIds, ',');
        Arrays.stream(ids).forEach(id -> {
            System.out.println(Integer.valueOf(id));

        });
    }
    
    
    
    
    @Test
    public void testStringFormat() {
        System.out.println(String.format("user is: %s", new User("name", 20)));
        
    }
    
    
    @Test
    public void testMessageFormat() {
    	String msg = "my name is {0}, I am {1} years old, work in {2}";
    	Object[] args = new Object[]{"yangkai", 28, "hujiang"};
    	String retnMsg = MessageFormat.format(msg, args);
    	System.out.println(retnMsg);
    }
    
    public static class User {
        private String name;
        private int age;
        
        
        public User(String name, int age) {
            super();
            this.name = name;
            this.age = age;
        }
        
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }

        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        }
        
        
    }

    @Test
    public void test_(){
/*        List<String> ids = new ArrayList<String>();
        ids.add("a");
        ids.add("b");
        ids.add("c");
        ids.add("d");
        ids.add("e");

        String str = StringUtils.join(ids, ',');
        System.out.println(str);*/

/*        List<User> users =  new ArrayList<User>();
            User u1 = new User();
            u1.setId(1);
            u1.setName("a");
            User u2 = new User();
            u2.setId(2);
            u2.setName("b");
            User u3 = new User();
            u3.setId(3);
            u3.setName("c");
            users.add(u1);
            users.add(u2);
            users.add(u3);

            String str2 = StringUtils.join(users, ',');

            System.out.println(str2);*/

/*        String a = "55d102e1b254477d8fa20f5c5b351358";
        System.out.println(a.concat("-rebate-order-daily-count"));*/



        String[] arrs = new String[]{"55d102e1b254477d8fa20f5c5b351358","65d102e1b254477d8fa20f5c5b351358","75d102e1b254477d8fa20f5c5b351358"};
        String suffix = null;// "-rebate-order-daily-count";
        List<String> keys = new ArrayList<String>();
        System.out.println(arrs);
        for (String arr : arrs) {
            System.out.println("before: " + arr);
        }

        for (String arr : arrs) {
            arr = arr.concat(suffix);
            //arr = arr + suffix;
            keys.add(arr);
            System.out.println("in: " + arr);
        }
        //arrs = keys.toArray(new String[]{});
        arrs = keys.toArray(arrs);

        for (String arr : arrs) {
            System.out.println("after: " + arr);
        }
        System.out.println(arrs);

    }


    @Test
    public void testString() {
        String str1 = "str";
        String str2 = "str";
        String str3 = new String("str");
        System.out.println("str1 == str2 : " + (str1 == str2));
        System.out.println("str1.equals(str2) :　" + str1.equals(str2));

        System.out.println("str1 == st3 : " + (str1 == str3));
        System.out.println("str1.equals(str3) : " + str1.equals(str3));
    }


    @Test
    public void testSplit_(){

        String[] ss = StringUtils.split("status,,,,,,createTime,updateTime", ",");
        for (String s : ss) {
            System.out.println(s);
        }
        String[] arr = {"stauts", "name", "alias"};
        String str = StringUtils.join(arr, ":");
        System.out.println(str);

    }

    @Test
    public void testSplit2(){

        String[] ss = StringUtils.split("x", ",");
        for (String s : ss) {
            System.out.println(s);
        }
        String[] arr = {"y","z"};
        String str = StringUtils.join(arr, ":");
        System.out.println(str);

    }

    @Test
    public void testSplit3() {
        MessageBody body = new MessageBody();
        body.setTouser("   1,   2,3");
        System.out.println("---" + body.getTouser() + "---");

    }

    public static class MessageBody {
        private String touser;

        public String getTouser() {
            return touser;
        }

        public void setTouser(String touser) {
            if (StringUtils.isNoneBlank(touser)) {
                List<String> temptouser = new ArrayList<>();
                Arrays.asList(touser.trim().split(",")).forEach(id -> temptouser.add(id.trim()));
                touser = StringUtils.join(temptouser, ",");
            }
            this.touser = touser;
        }
    }
    
    @Test
    public void testMutiStr() {
    	String a = null,b = null,c = "test";
    	System.out.println(a);
    	System.out.println(b);
    	System.out.println(c);
    }
    

}
