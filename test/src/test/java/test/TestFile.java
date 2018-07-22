package test;

import org.junit.Test;

import java.io.File;

/**
 * Created by yangkai on 2017/7/7.
 */
public class TestFile {

    @Test
    public void test() {
        String path = "xxx/yyy/mmm.png";
        String[] s = path.split(File.separator);
        System.out.println(s[s.length - 1]);
    }
}
