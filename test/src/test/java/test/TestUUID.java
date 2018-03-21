package test;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.junit.Test;

import java.math.BigInteger;

/**
 * Created by yangkai on 2017/10/15.
 */
public class TestUUID {

    @Test
    public void test() {
        HashFunction sha1 = Hashing.sha1();
        HashCode hashCode = sha1.hashString("leoliangyixun@163.com", Charsets.UTF_8);
        String hexStr = hashCode.toString().substring(0, 16);
        BigInteger bigInteger = new BigInteger(hexStr, 16);
        int shardNum = bigInteger.mod(BigInteger.valueOf(64)).intValue();
        System.out.println(shardNum);
    }
}
