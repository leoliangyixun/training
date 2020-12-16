package com.training;

public class Contracts {
    public final static String REDIS_KEY_BASE = "A_N_T";
    public final static String REDIS_KEY_TEMPLATE_BATCH_ID = Contracts.REDIS_KEY_BASE+ "_" + "TBI";
    public final static String REDIS_KEY_TEMPLATE = Contracts.REDIS_KEY_BASE + "_" + "T";
    public final static String REDIS_KEY_TEMPLATE_MESSAGE_BATCH_ID = Contracts.REDIS_KEY_BASE + "_" + "TMBI";
    public final static String REDIS_KEY_TEMPLATE_MESSAGE = Contracts.REDIS_KEY_BASE + "_" + "TM";
    public final static String REDIS_KEY_CHANNEL_FREQUENCY_DAY = Contracts.REDIS_KEY_BASE + "_" + "CFD";
    public final static String REDIS_KEY_TEMPLATE_FREQUENCY_DAY = Contracts.REDIS_KEY_BASE + "_" + "TFD";

    /**
     * PASSWORD_KEY变量只在同目录下Helper类中被使用，因此 protected
     */
    protected final static byte[] PASSWORD_KEY = new byte[] { 'h', 'j', 'n', 'o', 't', 'i', 'f', 'y' };
    /**
     * PASSWORD_IV变量只在同目录下Helper类中被使用，因此 protected
     */
    protected final static byte[] PASSWORD_IV = new byte[] { 'h', 'j', 't', 'p', 'm', 's', 'g', 's' };
}
