package com.hujiang.hjapi;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Created by xiawenbin on 2017/1/4.
 */

/**
 * 加密相关工具类
 */
@Slf4j
public class SecurityHelper {
    /**
     * SHA1加密
     *
     * @param s 需要加密的字符串
     * @return 加密后结果
     */
    public static String sha1Encrypt(String s) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            log.error("SecurityHelper.sha1Encrypt", e);
        }
        return "";
    }

    /**
     * 16位md5加密，小写
     *
     * @param s 需要加密的字符串
     * @return 加密结果
     */
    public static String md5_16(String s) {
        String md5 = md5(s);
        if (md5 != null) {
            return md5.toLowerCase().substring(8, 24);
        }
        return null;
    }

    /**
     * md5加密
     *
     * @param s 需要加密的字符串
     * @return 加密结果
     */
    public static String md5(String s) {
        char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                'e', 'f'};
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte tmp[] = mdTemp.digest(); // MD5 的计算结果是一个 128 位的长整数，
            // 用字节表示就是 16 个字节
            char strs[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
            // 所以表示成 16 进制需要 32 个字符
            int k = 0; // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
                // 转换成 16 进制字符的转换
                byte byte0 = tmp[i]; // 取第 i 个字节
                strs[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
                // >>> 为逻辑右移，将符号位一起右移
                strs[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
            }
            return new String(strs).toLowerCase(); // 换后的结果转换为字符串
        } catch (Exception e) {
            log.error("SecurityHelper.md5", e);
            return null;
        }
    }

    private final static String KEY = "Jkk5mDVkivmXide72+nX3+oHwbvF6F5S";
    private final static String IV = "k1bzhy2KqMY=";

    /**
     * 生成使用rng加密的随机字符串
     *
     * @return
     */
    public static String getRngCryptoRandom() {

        SecureRandom random = new SecureRandom();

        byte bytes[] = new byte[12];

        random.nextBytes(bytes);

        Base64.Encoder encoder = Base64.getEncoder();

        String s = encoder.encodeToString(bytes);

        return s.substring(0, 16);
    }


    public static String desEncrypt(String s) {

        try {

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] _key = decoder.decodeBuffer(KEY);
            byte[] _iv = decoder.decodeBuffer(IV);

            Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE,
                    new SecretKeySpec(_key, "DESede"),
                    new IvParameterSpec(_iv));
            byte[] encrypted = c.doFinal(s.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception ex) {
            log.error("SecurityHelper.desEncrypt", ex);
            return null;
        }
    }

    public static String desDecrypt(String s) {

        try {

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] _key = decoder.decodeBuffer(KEY);
            byte[] _iv = decoder.decodeBuffer(IV);

            Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE,
                    new SecretKeySpec(_key, "DESede"),
                    new IvParameterSpec(_iv));
            byte[] bytes = c.doFinal(Base64.getDecoder().decode(s));
            return new String(bytes, "UTF-8");

        } catch (Exception ex) {
            log.error("SecurityHelper.desDecrypt", ex);
            return null;
        }
    }


    public static String base64(String s) {

        try {
            return Base64.getEncoder().encodeToString(s.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            log.error("SecurityHelper.base64", ex);
            return "";
        }

    }

}
