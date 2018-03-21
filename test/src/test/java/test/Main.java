/**
 * 
 */
package test;

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

    @Test
    public void test2() {
        for (int i = 0; i < 64; i++) {
            String str = "DROP TABLE IF EXISTS `user_union_" + i + "`;\n" + "CREATE TABLE `user_union_" + i + "` (\n" + "  `id` int unsigned NOT NULL AUTO_INCREMENT,\n" + "  `union_id` bigint NOT NULL,\n" + "  `user_id` int(10) NOT NULL,\n" + "  `user_domain` varchar(10) NOT NULL,\n" + "  `create_at` datetime NOT NULL ,\n" + "  `update_at` datetime NOT NULL,\n" + "  PRIMARY KEY (`id`),\n" + "  UNIQUE KEY `uidx_user_id_user_domain` (`user_id`,`user_domain`) USING BTREE\n" + ") ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;";

            System.out.println(str);
            System.out.println();
        }

        for (int i = 0; i < 64; i++) {
            String str = "DROP TABLE IF EXISTS `union_user_" + i + "`;\n" + "CREATE TABLE `union_user_" + i + "` (\n" + "  `id` int unsigned NOT NULL AUTO_INCREMENT,\n" + "  `union_id` bigint NOT NULL,\n" + "  `user_id` int(10) NOT NULL,\n" + "  `user_domain` varchar(10) NOT NULL,\n" + "  `create_at` datetime NOT NULL ,\n" + "  `update_at` datetime NOT NULL,\n" + "  PRIMARY KEY (`id`),\n" + "  UNIQUE KEY `uidx_user_id_user_domain` (`user_id`,`user_domain`) USING BTREE,\n" + "  INDEX `idx_union_id_user_domain` (`union_id`, `user_domain`) USING BTREE\n" + ") ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;";

            System.out.println(str);
            System.out.println();
        }
    }


}
