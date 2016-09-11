/**
 * 
 */
package com.b5m.training.utils;

import java.util.Date;


/**
 * @author yangkai
 *
 */
public class Utils {
    

    
    public DateUtils createDateUtils() {
        return new DateUtils();
    }
    
    public static void main(String[] args) {
        Date now = Utils.DateUtils.format();
        System.out.println(now);
    }
    
    public static class DateUtils {
        public static Date format() {
            return new Date();
        }
    }

}
