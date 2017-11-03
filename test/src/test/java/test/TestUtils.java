package test;

import com.hujiang.basic.framework.core.util.JsonUtil;
import com.hujiang.passport.SecurityHelper;
import com.training.Utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TestUtils {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User {
        private String name;
        private int age;

        public String toString() {
            return JsonUtil.object2JSON(this);
        }
    }

    @Test
    public void testMapMD5() {
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> map3 = new HashMap<>();

        map1.put("school", "wuhan");
        map1.put("user", new User("gwj", 18));

        map2.put("school", "xiamen");
        map2.put("user", new User("yk", 20));

        map3.put("school", "wuhan");
        map3.put("user", new User("gwj", 18));

        System.out.println(JsonUtil.object2JSON(map1));
        System.out.println(JsonUtil.object2JSON(map2));
        System.out.println(JsonUtil.object2JSON(map3));

        System.out.println(Utils.md5(JsonUtil.object2JSON(map1)));
        System.out.println(Utils.md5(JsonUtil.object2JSON(map3)));
        System.out.println(Objects.equals(Utils.md5(JsonUtil.object2JSON(map1)), Utils.md5(JsonUtil.object2JSON(map3))));

    }


    @Test
    public void testDesEncrypt() {
        String des = SecurityHelper.desEncrypt(Utils.generateFixedStr(32));
        System.out.println(des);
    }
}
