package com.hujiang.passport;


import com.hujiang.basic.framework.core.sercurity.DES;
import com.hujiang.basic.framework.core.sercurity.MD5;
import com.hujiang.basic.framework.core.sercurity.RSA;
import com.training.Utils;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by qinjiangfeng on 2017/5/12.
 */
@Slf4j
public class SecurityHelper {


    //region MD5

    /**
     * MD5 32位加密 暂未实现 预留入口
     *
     * @param key
     * @return
     */
    public static String md5(String key) {

        return MD5.encryptMD5(key);
    }

    //endregion

    //region des

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
    //endregion

    //region RSA

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

    //endregion

    //region base64
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

    public static String base64encodeFrombyte(byte[] byteArray) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(byteArray);
    }

    public static byte[] base64decodeTobyte(String base64EncodedString) {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            return base64Decoder.decodeBuffer(base64EncodedString);
        } catch (IOException e) {
            return null;
        }
    }
    //endregion

    public static String encryptInfo(String source, int start, int length, int encryLength) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(source) && source.length() >= (start + length)) {
            String str = source.substring(0, start);
            str += org.apache.commons.lang3.StringUtils.repeat("*", encryLength);
            str += source.substring(start + length);
            return str;
        }
        return "";
    }

}
