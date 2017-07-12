/**
 * 
 */
package com.b5m.test.se;

import java.io.Serializable;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import com.hujiang.basic.framework.core.util.JsonUtil;

/**
 * @author yangkai
 *
 */
public class Main {
    
    @Test
    public void test() {
        Employee e = new Employee(200, "ok", "{}");
        e.setName("xx");
        //System.out.println(e.toString());
        System.out.println(e.clearSensitive());
    }

    @Data
    @NoArgsConstructor
    public static abstract class Entity<T extends Serializable> implements Serializable {
        
        private static final long serialVersionUID = 3454837606164676885L;

        protected Integer status;
        protected String message;
        protected T data;
        
        public Entity(Integer status, String message, T data) {
            this.status = status;
            this.message = message;
            this.data = data;
        }
        
        public String clearSensitive() {
            Entity<T> obj = JsonUtil.deserialize(JsonUtil.serialize(this));
            return obj.toString();
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }
    }

    @NoArgsConstructor
    @EqualsAndHashCode(callSuper=true)
    public static class Employee extends Entity<String> {
 
        private static final long serialVersionUID = -4742391741835085542L;
        @Getter
        @Setter
        private String name;

        public Employee(Integer status, String message, String data) {
            super(status, message, data);
            this.name = name;
        }
        
/*        public String clearSensitive() {
            Employee obj = JsonUtil.deserialize(JsonUtil.serialize(this));
            return obj.toString();
        }*/
        
        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }
        
    }

}
