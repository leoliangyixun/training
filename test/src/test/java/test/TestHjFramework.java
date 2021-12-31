package test;

import com.hujiang.basic.framework.core.util.JsonUtil;

import org.junit.Test;

import java.util.Map;

/**
 * @author yangkai
 * @date 2021/7/27
 * @email yangkai@hujiang.com
 * @description
 */
public class TestHjFramework {

    @Test
    public void test1() {
        String json = "{\"^inbox_message_.{1,}$\":\"inbox_message\"}";
        Map<String, Object> map = JsonUtil.json2Map(json);
        System.out.println(map);
    }

}
