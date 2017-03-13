/**
 * 
 */
package com.hujiang.framework.plugin.dfs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.hujiang.basic.framework.plugin.dfs.service.TakaService;
import com.hujiang.basic.framework.plugin.dfs.service.TakaFactory;

/**
 * @author yangkai
 *
 */
@Configuration
@PropertySource({ "classpath:application.properties" })
public class TestConfig {
	
    @Value("${taka.accessKey}")
    String accessKey;
    
    @Value("${taka.secretKey}")
    String secretKey;

    @Value("${taka.intranetUploadSite}")
    String intranetUploadSite;
    
    @Bean(name="qiniu")
    public TakaService takaService() {
    	return TakaFactory.getQiniuProxy(accessKey, secretKey, intranetUploadSite);
    }

}
