package test;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class TestCommonsCodec {

    @Test
    public void testMd5() {
        byte[] md5 = DigestUtils.md5("yangkai");
        System.out.println(md5);
    }
}
