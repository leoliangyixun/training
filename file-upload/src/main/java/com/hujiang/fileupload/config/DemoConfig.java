/**
 * 
 */
package com.hujiang.fileupload.config;

import javax.servlet.MultipartConfigElement;

import com.hujiang.basic.framework.plugin.picidentify.PicIdentifyPlugin;
import com.hujiang.basic.framework.plugin.picidentify.PicIdentifyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.hujiang.basic.framework.plugin.dfs.TakaPlugin;
import com.hujiang.basic.framework.plugin.dfs.service.TakaService;

/**
 * @author yangkai
 *
 */
@Configuration
public class DemoConfig {

    @Value("${taka.accessKey}")
    String accessKey;

    @Value("${taka.secretKey}")
    String secretKey;

    @Value("${taka.intranetUploadSite}")
    String intranetUploadSite;
    
    @Value("${taka.httpsEnable}")
    boolean httpsEnable;

    @Bean
    public TakaService takaService() {
        return TakaPlugin.getQiniuProxyInstance(accessKey, secretKey, intranetUploadSite, httpsEnable);
    }

    @Bean
    public PicIdentifyService picIdentifyService() {
        return PicIdentifyPlugin.getPicIdentifyService();
    }

    //@Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //// 设置文件大小限制 ,超了，页面会抛出异常信息，这时候就需要进行异常信息的处理了;
        factory.setMaxFileSize("128KB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("256KB");
        //Sets the directory location where files will be stored.
        //factory.setLocation("路径地址");
        return factory.createMultipartConfig();
    }

    //@Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }
}
