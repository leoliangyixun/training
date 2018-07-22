/**
 * 
 */
package com.training;

import com.hujiang.basic.framework.core.sercurity.DES;
import com.hujiang.basic.framework.core.sercurity.MD5;
import com.hujiang.basic.framework.core.sercurity.RSA;

import com.alibaba.druid.filter.config.ConfigTools;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections.CollectionUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author yangkai
 *
 */
@Slf4j
public class Utils {
    /**
     * MD5 32位加密
     *
     * @param key
     * @return
     */
    public static String md5(String key) {
        return MD5.encryptMD5(key);
    }

    public static String generateFixedStr(int totalWidth) {
        int tempTotalWidth = totalWidth <= 0 ? 1 : totalWidth;
        StringBuilder builder = new StringBuilder("");

        if (tempTotalWidth > 32) {
            builder.append(UUID.randomUUID().toString().replace("-", "")).append(generateFixedStr(tempTotalWidth - 32));
        } else {
            builder.append(UUID.randomUUID().toString().replace("-", "").substring(0, tempTotalWidth));
        }

        return builder.toString();
    }

    public static Set<Set<Long>> splitByTableIndex(Collection<Long> ids) {
        Asserts.notNull(ids, "illegal arguments");
        Map<Long, Set<Long>> map = Maps.newHashMap();
        ids.forEach(id -> {
            Long index = id % 64;
            if (map.containsKey(index)) {
                map.get(index).add(id);
            } else {
                map.put(index, Sets.newHashSet(id));
            }
        });

        return map.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toSet());
    }

    /**
     * @param ids
     * @return
     */
    public static Map<Long, List<Long>> splitByTableIndex2(Collection<Long> ids) {
        Asserts.notNull(ids, "illegal arguments");
        Map<Long, List<Long>> map = Maps.newHashMap();
        ids.forEach(id -> {
            Long tableShardingIndex = id % 64;
            if (map.containsKey(tableShardingIndex)) {
                map.get(tableShardingIndex).add(id);
            } else {
                map.put(tableShardingIndex, Lists.newArrayList(id));
            }
        });

        return map;
    }

    public static List<List<Long>> splitByTableIndex3(Collection<Long> ids) {
        Asserts.notNull(ids, "illegal arguments");
        Map<Long, List<Long>> map = Maps.newHashMap();
        ids.forEach(id -> {
            Long index = id % 64;
            if (map.containsKey(index)) {
                map.get(index).add(id);
            } else {
                map.put(index, Lists.newArrayList(id));
            }
        });

        return map.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    private static String KEY = "Jkk5mDVkivmXide72+nX3+oHwbvF6F5S";
    private static String IV = "k1bzhy2KqMY=";

    /**
     * DES 解密
     *
     * @param strText
     * @param key
     * @param iv
     * @return
     */
    public static String desDecrypt(String strText, String key, String iv) {
        return DES.des3DecodeCBC(key, iv, strText);
    }

    /**
     * DES 加密
     *
     * @param strText
     * @param key
     * @param iv
     * @return
     */
    public static String desEncrypt(String strText, String key, String iv) {
        return DES.des3EncodeCBC(key, iv, strText);
    }

    /**
     * DES 解密
     *
     * @param strText
     * @return
     */
    public static String desDecrypt(String strText) {
        try {
            return desDecrypt(strText, KEY, IV);
        } catch (Exception ex) {
            log.error("desDecrypt fail,text:" + strText, ex);
        }
        return "";
    }

    /**
     * DES 加密
     *
     * @param strText
     * @return
     */
    public static String desEncrypt(String strText) {
        return desEncrypt(strText, KEY, IV);
    }

    private static String RSA_Private = "<RSAKeyValue><Modulus>n02bCn6ePpQyZEr9VvBbieoi1TORChkh+oY4hdp/CfX/ZiC7e72bB8kSJC9gv6J2ujnjkzNqwhCsE9V4wBxSjHJ8qdJK3eNoPJloYoKSXCAKk5DHQq0pRMO2CbBZlboxSM727qgkQvI23xczw9Qmndt5m26Gik/NAdMz7i32wkM=</Modulus><Exponent>AQAB</Exponent><P>zh/QjeLGVqf3kUGNt+Q5+JhutozkGDTkVRrOA9uABtwv2o1oe9E74ZWTfQ7o0ySyP35fOktkTAzBl2HpjUIFrQ==</P><Q>xdl/aAHNHPwGloZgyT1pzmi3i7Rb9NDY05dyWQ2lmcGQiCKl+oAXWOCjQ6ftHal7zaWZTIk225RNNudzPKqFrw==</Q><DP>kXkYqpqrGkuPSN/4Ehmr5ExIjuPes0rY/0tcrJJEyBKsX1W/sOsZlPsIzZExNptGhdM9iBnfXmD+XEXJHF3FPQ==</DP><DQ>avsRTj7zFwIb2datjQM9LBQpc0xCEN3A8KDKfubHLZ2htkrt7sbGWROWlGW/7DMRs4AgpWbol9DX86ExCZO3Fw==</DQ><InverseQ>khNL53atdE550PvAZW2FHYUBRIBTsXkwDlzlxS59b06AxCqS5bclJfpXVAA5ICzjmf+DgP0KpFY4cpkl0gcE1w==</InverseQ><D>HyKi3905fo+mfbWuaDSYH490eWkXuvqIipd7KqY22DKNVV+mZRv81fu1oP7lkO7m96Ti1t1gy0dI6qN5c0rV0zCESabrEzdfCrlYm/PAHTfBwOxtAsxbxWBF0C6Ptkk5bZuECsOSL6ohbIVi63fYzBlu4WUZgN3AIUJfukTsPik=</D></RSAKeyValue>";

    private static String RSA_Public = "<RSAKeyValue><Modulus>n02bCn6ePpQyZEr9VvBbieoi1TORChkh+oY4hdp/CfX/ZiC7e72bB8kSJC9gv6J2ujnjkzNqwhCsE9V4wBxSjHJ8qdJK3eNoPJloYoKSXCAKk5DHQq0pRMO2CbBZlboxSM727qgkQvI23xczw9Qmndt5m26Gik/NAdMz7i32wkM=</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>";

    /**
     * RSA解密 预留入口
     *
     * @param strText
     * @return
     */
    public static String rsaDecrypt(String strText) {
        try {
            return RSA.decrypt(RSA_Private, strText);
        } catch (Exception ex) {
            log.error("SecurityHelper RSA decrypt fail", ex);
            return "";
        }
    }

    /**
     * RSA加密 预留入口
     *
     * @param strText
     * @return
     */
    public static String rsaEncrypt(String strText) {
        try {
            return RSA.encrypt(RSA_Public, strText);
        } catch (Exception ex) {
            log.error("SecurityHelper RSA decrypt fail", ex);
            return "";
        }

    }

    public static String base64Decrypt(String data) {
        byte[] b = null;
        String result = null;
        if (data != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(data);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                log.error(String.format("base64Decrypt fail ,data:%s", data), e);
            }
        }
        return result;
    }

    public static String base64Encrypt(String data) {
        byte[] b = null;
        String s = null;
        try {
            b = data.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            log.error(String.format("base64Decrypt fail ,data:%s", data), e);
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    public static String base64EncodeFromByte(byte[] byteArray) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(byteArray);
    }

    public static byte[] base64DecodeToByte(String base64EncodedString) {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            return base64Decoder.decodeBuffer(base64EncodedString);
        } catch (IOException e) {
            return null;
        }
    }

    public static String encryptInfo(String source, int start, int length, int encryLength) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(source) && source.length() >= (start + length)) {
            String str = source.substring(0, start);
            str += org.apache.commons.lang3.StringUtils.repeat("*", encryLength);
            str += source.substring(start + length);
            return str;
        }
        return "";
    }

    //拆分数组
    public static <T> Collection<Collection<T>> split(Collection<T> source, int quantity) {
        if (CollectionUtils.isEmpty(source)) {
            return Collections.emptyList();
        }

        int threshold = quantity <= 0 ? 100 : quantity;
        Collection<Collection<T>> target = new ArrayList<>();

        int count = 0;
        while (count < source.size()) {
            int fromIndex = count;
            int toIndex = (count + threshold) > source.size() ? source.size() : (count + threshold);
            target.add(new ArrayList<>(new ArrayList<>(source).subList(fromIndex, toIndex)));
            count += threshold;
        }

        return target;
    }

    private static final String PUBLIC_KEY ="MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAI12voYOca1M40kkcFD8aJd0sXLJQLSAVvCcI95WrKA6OFE1oz+sZkCr6JvQN0e4PkaTnO0ULdtAY6pru7K9evsCAwEAAQ==";
    
    public static String passwordDecode(String secret) {
        try {
            return ConfigTools.decrypt(PUBLIC_KEY, secret);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    private static HashFunction sha1 = Hashing.sha1();

    /**
     * 字符串进行分表计算
     * @param key 需要进行计算的键值
     * @param factor 分表数
     * @return 分表位置
     */
    public static int sharding(String key, int factor) {
        HashCode hashCode = sha1.hashString(key, Charsets.UTF_8);
        String hexStr = hashCode.toString().substring(0, 16);
        BigInteger bigInteger = new BigInteger(hexStr, 16);
        return bigInteger.mod(BigInteger.valueOf(factor)).intValue();
    }

    /**
     * 取模分表
     * @param key
     * @param factor
     * @return 分表位置
     */
    public static int sharding(long key, int factor) {
        return (int) key % factor;
    }

    public static int sharding(int key, int factor) {
        return key % factor;
    }


}
