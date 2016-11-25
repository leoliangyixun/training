/**
 * 
 */
package com.b5m.test.string;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;


/**
 * @author izene
 *
 */
public class StringTest {

    /**
     * 
     */
    public StringTest() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
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
    
    public static class User implements Serializable {

        private static final long serialVersionUID = -1138527483214027938L;
        
        private Integer id;
        private String name;
        public User() {
           
        }
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
    public void testSplit(){
    
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

}
