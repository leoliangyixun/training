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

    public StringTest() {
        // TODO Auto-generated constructor stub
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
   public void testIntern() {
       String s = "yangkai alisa";
       System.out.println(s.intern());
   }


}
