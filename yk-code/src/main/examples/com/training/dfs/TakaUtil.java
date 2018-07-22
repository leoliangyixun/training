package com.training.dfs;

/**
 * @author Jonathan Zhang
 * created at: 12/12/2016
 */
public class TakaUtil {
    private static final long TICKS_AT_EPOCH = 621355968000000000L;

    /**
     * Generate timestamp for taka service needs
     * @return
     */
    public static String generateTimestamp() {
        long millis = System.currentTimeMillis() % 1000;
        return String.valueOf(millis+TICKS_AT_EPOCH);
    }

}
