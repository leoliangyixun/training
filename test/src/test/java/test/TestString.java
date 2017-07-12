/**
 * 
 */
package test;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.b5m.test.enumeration.SourceType;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hujiang.basic.framework.core.util.JsonUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringEscapeUtils;
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
    public void format() {
        //System.out.println(String.format("user is: %s", new User("name", 20)));
        //NOT WORK System.out.println(String.format("user is: {1}, age: {2}", "yk", 20));
        System.out.println(String.format("user is: %s", "yk"));
        System.out.println(String.format("%s/qc/?callbacktype=%d", "yk", 10));
        System.out.println(String.format("%s/bind/result", "/"));
        System.out.println(StandardCharsets.UTF_8.name());
        System.out.println(String.format("%s%ssource=qq%sid={0}%s", "pass.hujang.com", "?", "&", "/"));

        System.out.println(String.format("%s/qc/?callbacktype=%s", "yk", 10));

        
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

    @Test
    public void testSplit4() {
        String[] ss = StringUtils.split(null , ",");
        System.out.println(ss);

    }

    @Test
    public void testSplit5() {
        Set<String> ids =Sets.newHashSet(StringUtils.split(null, ","));
        System.out.println(ids);

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
    
    @Test
    public void testUUID() {
    	String uuid = UUID.randomUUID().toString();
    	System.out.println(uuid);
    	String widgetKey = uuid.replace("-", "");
    	System.out.println(widgetKey);
    }

    @Test
    public void testStartWith() {
        String key ="Wechat_qapass";
        System.out.println("--------------startsWith----------------");
        System.out.println(key.startsWith("Wechat"));
        System.out.println(key.startsWith("wechat"));
        System.out.println(key.startsWith("Wechat_"));
        System.out.println(key.startsWith("Wechat_qa"));
        System.out.println("--------------contains----------------");
        System.out.println(key.contains("Wechat"));
        System.out.println(key.contains("wechat"));
        System.out.println(key.contains("Wechat_"));
        System.out.println(key.contains("Wechat_qa"));
    }

    @Test
    public void test() {
    	System.out.println(StringUtils.split("1", ","));
    	//-----------------------------------------------------------------
        String a = new String("xxx");
        String b = new String("xxx");
        String c = new String("xxx");
        String d = new String("ggmm");
        String e = "xxx";
        String f ="xxx";
        Set<String> set = new HashSet<String>();
        set.add(a);
        set.add(b);
        set.add(c);
        set.add(d);
        set.add(e);
        System.out.println(set);
        System.out.println("a==b: " + a == b);
        System.out.println("a.equals(b): " + a.equals(b));
        System.out.println(set.contains(new String("xxx")));
        System.out.println(set.contains("xxx"));
        System.out.println("e==f:" + (e==f));
//-----------------------------------------------------------------
        String x ;
        String y ="yyy";
        x = y;
        System.out.println(x);
    }
    
    @Test
    public void testReplace() {
    	String a = "httrp://www.google.com";
    	System.out.println(a.replaceFirst("http", "https"));
    	
    	List<Info> infos = Lists.newArrayList(new Info("http://xxx"), new Info("http;//xxx"),new Info("http;//xxx"),new Info("https://xxx"));
    	System.out.println(infos);
    	infos.forEach(e -> {
    		
    		if (!e.getUrl().startsWith("https")) {
    			e.setUrl(e.getUrl().replaceFirst("http", "https"));
    		}
    	});
    	System.out.println(infos);
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Info {
    	private String url;

		@Override
		public String toString() {
			return JsonUtil.object2JSON(this);
		}
    }
    
    @Test
    public void test10() {
       String data ="|||#ffffff";

       String[] pairs = StringUtils.split(data, "|||");
       System.out.println(pairs);
    }

    @Test
    public void test11() {
        String data ="xxx";

        String[] pairs = StringUtils.split(data, "|||");
        System.out.println(pairs);
    }
    
    @Test
    public void test12() {
        String data ="e";
        String[] pairs = StringUtils.split(data, "|||");
        System.out.println(pairs);
    }

    @Test
    public void test13() {
        String a = "01";
        System.out.println(StringUtils.isNumeric(a));
    }

    @Test
    public void test14() {
        String a = "你好";
        char[] chars = a.toCharArray();
        System.out.println(chars.length);
        for (char c : chars) {
            System.out.println(c + ",");
        }
    }

    @Test
    public void test15() {
        String a = "你好，。？";
        //String str = a.replaceAll("[^x00-xff]*", "*");
        String str = a.replaceAll("[\\u4e00-\\u9fa5]", "*");

        ///([u4e00-u9fa5])/
        //boolean isAscii = CharMatcher.ASCII.matchesAllOf(headerVal);
        System.out.println(str);
    }

    @Test
    public void test16() {
        String a = "abcde1？你";
        for (char c : a.toCharArray()) {
            System.out.println((int) c);
        }

    }
    @Test
    public void test17() {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        uuid = uuid.replace("-", "");
        System.out.println(uuid.length());
        System.out.println(uuid);

        System.out.println(uuid.substring(0, 31));

    }

    @Test
    public void test18() {
       // String a = "\"yangkai\"";
        // String a = "\"yangkai";
        // String a = "\"\"";
        String a = "\"";
        System.out.println("原始值: " + a);
        System.out.println("杨开的方法: " + removeFirstAndLastQuote(a));
        System.out.println("余老师的方法: " + removeFirstAndLastQuote2(a));


    }

    //before refactor
    public static String removeFirstAndLastQuote(String str) {
        if (StringUtils.isNotBlank(str)) {
            if (str.indexOf("\"") == 0) {
                str = str.substring(1, str.length());
            }  //去掉第一个"

            if (str.lastIndexOf("\"") == (str.length() - 1)) {
                str = str.substring(0, str.length() - 1);
            } //去掉最后一个"
        }

        return str;
    }

    // after refactor
    private static final char QUOTE_CHAR = '"';
    public static String removeFirstAndLastQuote2(String str) {
        if (StringUtils.isNotBlank(str)) {
            int startOffset = 0;
            int endOffset = str.length() - 1;
            if (str.charAt(startOffset) == QUOTE_CHAR) {
                startOffset = Math.min(startOffset + 1, endOffset);
            } // 去掉第一个"

            if (str.charAt(endOffset) == QUOTE_CHAR) {
                endOffset = Math.max(endOffset, 0);
            } // 去掉第一个"

            return str.substring(startOffset, endOffset);

			/*
			 * if (str.lastIndexOf("\"") == (str.length() - 1)) {
			 * str = str.substring(0, str.length() - 1);
			 * } //去掉最后一个"
			 */
        }

        return str;
    }


    @Test
    public void test19() {
        //String a = "<script>alert(\"hello\"); &&</script>";
        String a = "hello word";
        System.out.println(a);
        a =StringEscapeUtils.escapeHtml4(a);
        System.out.println(a);
        a =StringEscapeUtils.unescapeHtml4(a);
        System.out.println(a);
    }

    @Test
    public void test20() {
        String a = "title:xxx, url:www.baidu.com^-^title:yyy, url:www.google.com";
        String b = "title:xxx, url:www.baidu.com";
        String c = "{\"title\":\"xxx\", \"url\":\"www.baidu.com\"}";
        String d = "{title:xxx, url:www.baidu.com}";
        JSONObject jo = JSONObject.parseObject(d);

        String[] as = a.split("^-^");

        System.out.println();
    }


}
