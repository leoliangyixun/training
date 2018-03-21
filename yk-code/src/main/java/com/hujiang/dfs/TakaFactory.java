package com.hujiang.dfs;

/**
 * @author Jonathan Zhang
 * created at: 12/12/2016
 */

public class TakaFactory {
    
    public static TakaService getQiniuProxy(String accessKey, String secretKey, String intranetUploadSit) {
    	return new QiniuProxy(accessKey,secretKey,intranetUploadSit);
    }
}
