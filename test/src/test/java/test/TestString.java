/**
 * 
 */
package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import com.b5m.test.enumeration.SourceType;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hujiang.basic.framework.core.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import sun.nio.ch.IOUtil;

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

        String str = Utils.join(ids, ',');
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

            String str2 = Utils.join(users, ',');

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
        //String a = "yangkai";
        String a = "\"yangkai\"";
        // String a = "\"yangkai";
        // String a = "\"\"";
        //String a = "\"";
        System.out.println("原始值: " + a);
        //System.out.println("杨开的方法: " + removeFirstAndLastQuote(a));
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


    @Test
    public void test21() {
        String s = "\"";
        System.out.println(s.substring(1, s.length()).equals(""));
    }

    public static String removeFirstAndLastQuote3(String str) {
        if (StringUtils.isNotBlank(str)) {
            //str = str.trim();
            int start = 0;
            int length = str.length();
            int end = length;
            if (str.charAt(start) == QUOTE_CHAR) {
                start++;
            }

            if (str.charAt(end - 1) == QUOTE_CHAR) {
                end--;
            }

            str = end >= start ? str.substring(start, end) :str.substring(start, length);

        }
        return str;
    }

    @Test
    public void test22() {
        String a = "yangkai";
        String b = "\"yangkai\"";
        String c = "\"yangkai";
        String d = "yangkai\"";
        String e = "\"\"";
        String f = "\"";
        String g = "\" ";
        String h = " \"";
        String i = " \"yangkai \" ";
        String j = "yangkai \" ";
        String k = " \"yangkai";
        String l = " \"yangkai ";
        String m = " \"   \"";
        String n = " \"'yangkai '";
        String o = "\"y\"";
        String p = "\"y";
        String q = "y\"";
        String r = "";
        System.out.println("a --> length:" + a.length() + " origin:" + "{" + a + "}" + " {" + removeFirstAndLastQuote3(a) + "}");
        System.out.println("b --> length:" + b.length() + " origin:" + "{" + b + "}" + " {" + removeFirstAndLastQuote3(b) + "}");
        System.out.println("c --> length:" + c.length() + " origin:" + "{" + c + "}" + " {" + removeFirstAndLastQuote3(c) + "}");
        System.out.println("d --> length:" + d.length() + " origin:" + "{" + d + "}" + " {" + removeFirstAndLastQuote3(d) + "}");
        System.out.println("e --> length:" + e.length() + " origin:" + "{" + e + "}" + " {" + removeFirstAndLastQuote3(e) + "}");
        System.out.println("f --> length:" + f.length() + " origin:" + "{" + f + "}" + " {" + removeFirstAndLastQuote3(f) + "}");
        System.out.println("g --> length:" + g.length() + " origin:" + "{" + g + "}" + " {" + removeFirstAndLastQuote3(g) + "}");
        System.out.println("h --> length:" + h.length() + " origin:" + "{" + h + "}" + " {" + removeFirstAndLastQuote3(h) + "}");
        System.out.println("i --> length:" + i.length() + " origin:" + "{" + i + "}" + " {" + removeFirstAndLastQuote3(i) + "}");
        System.out.println("j --> length:" + j.length() + " origin:" + "{" + j + "}" + " {" + removeFirstAndLastQuote3(j) + "}");
        System.out.println("k --> length:" + k.length() + " origin:" + "{" + k + "}" + " {" + removeFirstAndLastQuote3(k) + "}");
        System.out.println("l --> length:" + l.length() + " origin:" + "{" + l + "}" + " {" + removeFirstAndLastQuote3(l) + "}");
        System.out.println("m --> length:" + m.length() + " origin:" + "{" + m + "}" + " {" + removeFirstAndLastQuote3(m) + "}");
        System.out.println("n --> length:" + n.length() + " origin:" + "{" + n + "}" + " {" + removeFirstAndLastQuote3(n) + "}");
        System.out.println("o --> length:" + o.length() + " origin:" + "{" + o + "}" + " {" + removeFirstAndLastQuote3(o) + "}");
        System.out.println("p --> length:" + p.length() + " origin:" + "{" + p + "}" + " {" + removeFirstAndLastQuote3(p) + "}");
        System.out.println("q --> length:" + q.length() + " origin:" + "{" + q + "}" + " {" + removeFirstAndLastQuote3(q) + "}");
        System.out.println("r --> length:" + r.length() + " origin:" + "{" + r + "}" + " {" + removeFirstAndLastQuote3(r) + "}");
    }

    @Test
    public void test23() {
        String a  = "abcd";
        System.out.println(a.substring(0, 0).equals(""));
        System.out.println(a.substring(0, 100));
    }

    @Test
    public void testArrayContain() {
        String[] a  = {"a", "b", "c"};
        String x = null;
        System.out.println(ArrayUtils.contains(a, x));

    }

    @Test
    public void testArrayContain2() {
        SendType[] types  = SendType.values();
        String type = null;
        System.out.println(ArrayUtils.contains(types, SendType.convert(type)));
        type = "mass";
        System.out.println(ArrayUtils.contains(types, SendType.convert(type)));

    }

    public enum SendType {
        applet, tmpl, mass;

        public static SendType convert(String value) {
            return Arrays.stream(values()).filter(e -> e.name().equals(value)).findFirst().orElse(null);
        }
    }


    @Test
    public void test24() {
        String a  = "abcd";
        System.out.println(a.substring(0, 100));// error
        System.out.println(StringUtils.substring(a, 0, 100));
    }

    @Test
    public void test25() {
        String a  = "123456_abcd.png";
        String[] ss = StringUtils.split(a, ".");
        System.out.println(ss);
    }


    @Test
    public void test26() {
        String a  = "";
        String b  = null;
      //  System.out.println(Integer.valueOf(a));
        System.out.println(Integer.valueOf(b));
    }


    @Test
    public void test27() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i< 300; i++) {

            for (int j = 0; j< 8; j ++) {
                sb.append(new Random().nextInt(9));
            }
            sb.append(",");
        }
        System.out.println(sb.toString());
    }

    @Test
    public void testGenUUID() throws Exception {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            builder.append("\"").append(UUID.randomUUID().toString()).append("\",").append("\n");
        }
        System.out.println(builder.toString());
    }

    @Test
    public void testGenUUIDUpcase() throws Exception {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            builder.append("\"").append(UUID.randomUUID().toString().toUpperCase()).append("\",").append("\n");
        }
        System.out.println(builder.toString());
    }


    @Test
    public void test2() {
        String str = null;
        System.out.println(String.format("token:%s", str));
    }

    @Test
    public void testStrip() {
        String source = "www.hujiang.com/login";
        String target ="http://www.hujiang.com/login?source=app&appid=20091024538";

        if (StringUtils.startsWithIgnoreCase(source, "http://")) {
            source = StringUtils.stripStart(source, "http://");
        } else if (StringUtils.startsWithIgnoreCase(source, "https://")) {
            source = StringUtils.stripStart(source, "https://");
        }
        System.out.println(source);

        if (StringUtils.startsWithIgnoreCase(target, "http://")) {
            target = StringUtils.stripStart(target, "http://");
        } else if (StringUtils.startsWithIgnoreCase(target, "https://")) {
            target = StringUtils.stripStart(target, "https://");
        }

        target = StringUtils.split(target, "?")[0];

        System.out.println(target);

        System.out.println(StringUtils.contains(target, source));
        System.out.println(Objects.equals(source, target));


    }

    @Test
    public void testURLEncode() throws Exception{
        String mobile = "13166016298";
        String email = "leoliangyixun@163.com";
        System.out.println(URLEncoder.encode(mobile, StandardCharsets.UTF_8.name()));
        System.out.println(URLEncoder.encode(email, StandardCharsets.UTF_8.name()));
    }

    @Test
    public void testStirng() {
        String a = "{\"name\": \"yangkai\"}";
        String json = JsonUtil.json2Object(a, String.class);
        System.out.println(json);
    }

    @Test
    public void testSplit6() {
        String s = "123 , 456, 789  ,  345  , 675,  245";
        Set<String> ids;
        ids = Arrays.stream(StringUtils.split(s, ","))
                .map(StringUtils::trim).collect(Collectors.toSet());
        System.out.println(ids);

        ids = Arrays.stream(StringUtils.split(s, ","))
                .map(String::trim).collect(Collectors.toSet());
        System.out.println(ids);

        ids = Arrays.stream(StringUtils.split(s, ",")).collect(Collectors.toSet());
        System.out.println(ids);


    }

    @Test
    public void testSplit7() {
        Set<String> ids = Arrays.stream(StringUtils.split("", ","))
                .map(StringUtils::trim).collect(Collectors.toSet());
        System.out.println(ids);
        System.out.println(ids.isEmpty());
        System.out.println(ids.size());
    }

    @Test
    public void testSplit8() {
        Set<String> ids = Sets.newHashSet();
        String touser = StringUtils.join(ids, ",");
        System.out.println(StringUtils.isBlank(touser));
        System.out.println(StringUtils.isEmpty(touser));
        System.out.println(touser == null);
    }


    @Test
    public void testUrl() throws Exception {
        String s = "https://pass.hujang.com/v1/qc/?source=xxx&name=yk";
        URL url = new URL(s);
        System.out.println(url.getHost());
        System.out.println(url.getQuery());
    }

    @Test
    public void testUrl2() throws Exception {
        String s = "http%3A%2F%2Fqapass.cctalk.com%2Fqc%3Fsource%3dHUJIANG%26callbacktype%3d0%26protocol%3dhttps%3A%26domain%3dhujiang.com%26bind_mobile%3d%26callbackUrl%3dhttp%3A%2F%2Fqapass.cctalk.com%2Fqc%26returnurl%3dhttp%253A%252F%252Fdevpass.hujiang.com%252Faccount%252F%26inputsource%3d%26version%3dv2";
        s =  URLDecoder.decode(s, "utf-8");
        URL url = new URL(s);
        System.out.println(url.getHost());
        System.out.println(url.getQuery());
    }

    @Test
    public void testStringTrip() {
        String str = "(x(x)x)";
        str = StringUtils.strip(str, "(");
        System.out.println(str);
    }

    @Test
    public void testStringTrip2() {
        String str = "http://pass.hujiang.com";
        System.out.println(str);
        str = StringUtils.stripStart(str, "http://");
        str = StringUtils.stripStart(str, "https://");
        System.out.println(str);
    }

    @Test
    public void testRemove() {
        String str = "https://pass.hujiang.com";
        System.out.println(str);
        str = StringUtils.removeStart(str, "http://");
        str = StringUtils.removeStart(str, "https://");
        System.out.println(str);
    }

    @Test
    public void testRemove2() {
        String str = "https://pass.hujiang.com";
        System.out.println(str);
        str = StringUtils.remove(str, "http://");
        str = StringUtils.remove(str, "https://");
        System.out.println(str);
    }

    @Test
    public void testStringLegnth() {
        String str = "[{\n" +
                "    \"item\": {\n" +
                "        \"id\": 10,\n" +
                "        \"key\": \"study_tool_h\",\n" +
                "        \"name\": \"学习工具配置\",\n" +
                "        \"value\": \"{\\n      \\\"items\\\": [\\n        {\\n          \\\"id\\\": \\\"com.hujiang.hjclass\\\",\\n          \\\"name\\\": \\\"沪江网校\\\",\\n          \\\"icon\\\": \\\"http://www.qiniu.com/a.png\\\",\\n          \\\"scheme\\\": \\\"hujiangclass3://hjclass.hujiang.com/\\\",\\n          \\\"url\\\": \\\"https://itunes.apple.com/cn/app/hu-jiang-wang-xiao-wai-yu/id738227542?l=zh&ls=1&mt=8\\\"\\n        },\\n        {\\n          \\\"id\\\": \\\"com.hujiang.cctalk\\\",\\n          \\\"name\\\": \\\"沪江CCTalk\\\",\\n          \\\"icon\\\": \\\"\\\",\\n          \\\"scheme\\\": \\\"hujiangcctalk://\\\",\\n          \\\"url\\\": \\\"https://itunes.apple.com/cn/app/hu-jiangcctalk-hu-dong-zhi/id843666882?l=zh&ls=1&mt=8\\\"\\n        },\\n        {\\n          \\\"id\\\": \\\"com.hujiang.dict\\\",\\n          \\\"name\\\": \\\"小D词典4G\\\",\\n          \\\"icon\\\": \\\"\\\",\\n          \\\"scheme\\\": \\\"hjdict://\\\",\\n          \\\"url\\\": \\\"https://itunes.apple.com/cn/app/hu-jiang-xiaod-ci-dian-ying/id481584414?l=zh&ls=1&mt=8\\\"\\n        },\\n        {\\n          \\\"id\\\": \\\"com.hjwordgames\\\",\\n          \\\"name\\\": \\\"开心词场4G\\\",\\n          \\\"icon\\\": \\\"\\\",\\n          \\\"scheme\\\": \\\"cichang://\\\",\\n          \\\"url\\\": \\\"https://itunes.apple.com/cn/app/hu-jiang-kai-xin-ci-chang/id635206028?l=zh&ls=1&mt=8\\\"\\n        }\\n      ],\\n      \\\"display\\\": true\\n    }\"\n" +
                "    },\n" +
                "    \"id\": 10\n" +
                "},{\n" +
                "    \"item\": {\n" +
                "        \"id\": 10,\n" +
                "        \"key\": \"study_tool_h\",\n" +
                "        \"name\": \"学习工具配置\",\n" +
                "        \"value\": \"{\\n      \\\"items\\\": [\\n        {\\n          \\\"id\\\": \\\"com.hujiang.hjclass\\\",\\n          \\\"name\\\": \\\"沪江网校\\\",\\n          \\\"icon\\\": \\\"http://www.qiniu.com/a.png\\\",\\n          \\\"scheme\\\": \\\"hujiangclass3://hjclass.hujiang.com/\\\",\\n          \\\"url\\\": \\\"https://itunes.apple.com/cn/app/hu-jiang-wang-xiao-wai-yu/id738227542?l=zh&ls=1&mt=8\\\"\\n        },\\n        {\\n          \\\"id\\\": \\\"com.hujiang.cctalk\\\",\\n          \\\"name\\\": \\\"沪江CCTalk\\\",\\n          \\\"icon\\\": \\\"\\\",\\n          \\\"scheme\\\": \\\"hujiangcctalk://\\\",\\n          \\\"url\\\": \\\"https://itunes.apple.com/cn/app/hu-jiangcctalk-hu-dong-zhi/id843666882?l=zh&ls=1&mt=8\\\"\\n        },\\n        {\\n          \\\"id\\\": \\\"com.hujiang.dict\\\",\\n          \\\"name\\\": \\\"小D词典4G\\\",\\n          \\\"icon\\\": \\\"\\\",\\n          \\\"scheme\\\": \\\"hjdict://\\\",\\n          \\\"url\\\": \\\"https://itunes.apple.com/cn/app/hu-jiang-xiaod-ci-dian-ying/id481584414?l=zh&ls=1&mt=8\\\"\\n        },\\n        {\\n          \\\"id\\\": \\\"com.hjwordgames\\\",\\n          \\\"name\\\": \\\"开心词场4G\\\",\\n          \\\"icon\\\": \\\"\\\",\\n          \\\"scheme\\\": \\\"cichang://\\\",\\n          \\\"url\\\": \\\"https://itunes.apple.com/cn/app/hu-jiang-kai-xin-ci-chang/id635206028?l=zh&ls=1&mt=8\\\"\\n        }\\n      ],\\n      \\\"display\\\": true\\n    }\"\n" +
                "    },\n" +
                "    \"id\": 10\n" +
                "},{\n" +
                "    \"item\": {\n" +
                "        \"id\": 10,\n" +
                "        \"key\": \"study_tool_h\",\n" +
                "        \"name\": \"学习工具配置\",\n" +
                "        \"value\": \"{\\n      \\\"items\\\": [\\n        {\\n          \\\"id\\\": \\\"com.hujiang.hjclass\\\",\\n          \\\"name\\\": \\\"沪江网校\\\",\\n          \\\"icon\\\": \\\"http://www.qiniu.com/a.png\\\",\\n          \\\"scheme\\\": \\\"hujiangclass3://hjclass.hujiang.com/\\\",\\n          \\\"url\\\": \\\"https://itunes.apple.com/cn/app/hu-jiang-wang-xiao-wai-yu/id738227542?l=zh&ls=1&mt=8\\\"\\n        },\\n        {\\n          \\\"id\\\": \\\"com.hujiang.cctalk\\\",\\n          \\\"name\\\": \\\"沪江CCTalk\\\",\\n          \\\"icon\\\": \\\"\\\",\\n          \\\"scheme\\\": \\\"hujiangcctalk://\\\",\\n          \\\"url\\\": \\\"https://itunes.apple.com/cn/app/hu-jiangcctalk-hu-dong-zhi/id843666882?l=zh&ls=1&mt=8\\\"\\n        },\\n        {\\n          \\\"id\\\": \\\"com.hujiang.dict\\\",\\n          \\\"name\\\": \\\"小D词典4G\\\",\\n          \\\"icon\\\": \\\"\\\",\\n          \\\"scheme\\\": \\\"hjdict://\\\",\\n          \\\"url\\\": \\\"https://itunes.apple.com/cn/app/hu-jiang-xiaod-ci-dian-ying/id481584414?l=zh&ls=1&mt=8\\\"\\n        },\\n        {\\n          \\\"id\\\": \\\"com.hjwordgames\\\",\\n          \\\"name\\\": \\\"开心词场4G\\\",\\n          \\\"icon\\\": \\\"\\\",\\n          \\\"scheme\\\": \\\"cichang://\\\",\\n          \\\"url\\\": \\\"https://itunes.apple.com/cn/app/hu-jiang-kai-xin-ci-chang/id635206028?l=zh&ls=1&mt=8\\\"\\n        }\\n      ],\\n      \\\"display\\\": true\\n    }\"\n" +
                "    },\n" +
                "    \"id\": 10\n" +
                "},{\n" +
                "    \"item\": {\n" +
                "        \"id\": 10,\n" +
                "        \"key\": \"study_tool_h\",\n" +
                "        \"name\": \"学习工具配置\",\n" +
                "        \"value\": \"{\\n      \\\"items\\\": [\\n        {\\n          \\\"id\\\": \\\"com.hujiang.hjclass\\\",\\n          \\\"name\\\": \\\"沪江网校\\\",\\n          \\\"icon\\\": \\\"http://www.qiniu.com/a.png\\\",\\n          \\\"scheme\\\": \\\"hujiangclass3://hjclass.hujiang.com/\\\",\\n          \\\"url\\\": \\\"https://itunes.apple.com/cn/app/hu-jiang-wang-xiao-wai-yu/id738227542?l=zh&ls=1&mt=8\\\"\\n        },\\n        {\\n          \\\"id\\\": \\\"com.hujiang.cctalk\\\",\\n          \\\"name\\\": \\\"沪江CCTalk\\\",\\n          \\\"icon\\\": \\\"\\\",\\n          \\\"scheme\\\": \\\"hujiangcctalk://\\\",\\n          \\\"url\\\": \\\"https://itunes.apple.com/cn/app/hu-jiangcctalk-hu-dong-zhi/id843666882?l=zh&ls=1&mt=8\\\"\\n        },\\n        {\\n          \\\"id\\\": \\\"com.hujiang.dict\\\",\\n          \\\"name\\\": \\\"小D词典4G\\\",\\n          \\\"icon\\\": \\\"\\\",\\n          \\\"scheme\\\": \\\"hjdict://\\\",\\n          \\\"url\\\": \\\"https://itunes.apple.com/cn/app/hu-jiang-xiaod-ci-dian-ying/id481584414?l=zh&ls=1&mt=8\\\"\\n        },\\n        {\\n          \\\"id\\\": \\\"com.hjwordgames\\\",\\n          \\\"name\\\": \\\"开心词场4G\\\",\\n          \\\"icon\\\": \\\"\\\",\\n          \\\"scheme\\\": \\\"cichang://\\\",\\n          \\\"url\\\": \\\"https://itunes.apple.com/cn/app/hu-jiang-kai-xin-ci-chang/id635206028?l=zh&ls=1&mt=8\\\"\\n        }\\n      ],\\n      \\\"display\\\": true\\n    }\"\n" +
                "    },\n" +
                "    \"id\": 10\n" +
                "},{\n" +
                "    \"item\": {\n" +
                "        \"id\": 10,\n" +
                "        \"key\": \"study_tool_h\",\n" +
                "        \"name\": \"学习工具配置\",\n" +
                "        \"value\": \"{\\n      \\\"items\\\": [\\n        {\\n          \\\"id\\\": \\\"com.hujiang.hjclass\\\",\\n          \\\"name\\\": \\\"沪江网校\\\",\\n          \\\"icon\\\": \\\"http://www.qiniu.com/a.png\\\",\\n          \\\"scheme\\\": \\\"hujiangclass3://hjclass.hujiang.com/\\\",\\n          \\\"url\\\": \\\"https://itunes.apple.com/cn/app/hu-jiang-wang-xiao-wai-yu/id738227542?l=zh&ls=1&mt=8\\\"\\n        },\\n        {\\n          \\\"id\\\": \\\"com.hujiang.cctalk\\\",\\n          \\\"name\\\": \\\"沪江CCTalk\\\",\\n          \\\"icon\\\": \\\"\\\",\\n          \\\"scheme\\\": \\\"hujiangcctalk://\\\",\\n          \\\"url\\\": \\\"https://itunes.apple.com/cn/app/hu-jiangcctalk-hu-dong-zhi/id843666882?l=zh&ls=1&mt=8\\\"\\n        },\\n        {\\n          \\\"id\\\": \\\"com.hujiang.dict\\\",\\n          \\\"name\\\": \\\"小D词典4G\\\",\\n          \\\"icon\\\": \\\"\\\",\\n          \\\"scheme\\\": \\\"hjdict://\\\",\\n          \\\"url\\\": \\\"https://itunes.apple.com/cn/app/hu-jiang-xiaod-ci-dian-ying/id481584414?l=zh&ls=1&mt=8\\\"\\n        },\\n        {\\n          \\\"id\\\": \\\"com.hjwordgames\\\",\\n          \\\"name\\\": \\\"开心词场4G\\\",\\n          \\\"icon\\\": \\\"\\\",\\n          \\\"scheme\\\": \\\"cichang://\\\",\\n          \\\"url\\\": \\\"https://itunes.apple.com/cn/app/hu-jiang-kai-xin-ci-chang/id635206028?l=zh&ls=1&mt=8\\\"\\n        }\\n      ],\\n      \\\"display\\\": true\\n    }\"\n" +
                "    },\n" +
                "    \"id\": 10\n" +
                "}]";
        System.out.println(str.length());
    }



}
