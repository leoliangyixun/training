/**
 * 
 */
package com.training;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.hujiang.basic.framework.core.sercurity.MD5;
import com.hujiang.passport.SecurityHelper;
import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

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

    public static void main(String[] args) {
        String str = generateFixedStr(64);
        System.out.println(str.toUpperCase());


        System.out.println(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        System.out.println(SecurityHelper.md5(uuid));
        System.out.println(SecurityHelper.desEncrypt(uuid));
    }

}
