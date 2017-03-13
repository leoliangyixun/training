package com.hujiang.framework.plugin.dfs;

import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;
import com.hujiang.basic.framework.core.util.EncryptUtils;
import com.hujiang.basic.framework.plugin.dfs.model.AdvanceFile;
import com.hujiang.basic.framework.plugin.dfs.model.SimpleFile;
import com.hujiang.basic.framework.plugin.dfs.model.UploadInfo;
import com.hujiang.basic.framework.plugin.dfs.service.QiniuProxy;
import com.hujiang.basic.framework.plugin.dfs.service.TakaService;
import com.hujiang.basic.framework.plugin.dfs.util.TakaUtil;
import com.hujiang.basic.framework.rest.common.RestClient;
import com.hujiang.framework.plugin.dfs.config.TestConfig;

/**
 * @author Jonathan Zhang created at: 12/12/2016
 */
@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@Import({TestConfig.class})
@ComponentScan({"com.hujiang.basic.framework.plugin.dfs"})
public class TakaServiceTest {
	
    @Value("${taka.accessKey}")
    String accessKey;
    
    @Value("${taka.secretKey}")
    String secretKey;

    @Value("${taka.intranetUploadSite}")
    String intranetUploadSite;

	@Autowired
	@Qualifier("qiniu")
    private TakaService takaService;
	
    @Test
    public void testSimpleUpload() throws Exception {
        SimpleFile file = new SimpleFile();
        ClassPathResource resource = new ClassPathResource("test.png");
        file.setRawFilename(resource.getFilename());
        file.setContent(IOUtils.toByteArray(resource.getInputStream()));
        UploadInfo info = takaService.simpleUpload(file);
        System.out.println(info);
    }

    @Test
    public void testSimpleUploadAll() throws Exception {
    	SimpleFile file1 = new SimpleFile();
    	SimpleFile file2 = new SimpleFile();
    	ClassPathResource resource1 = new ClassPathResource("test.png");
    	ClassPathResource resource2 = new ClassPathResource("jiangyiyan.jpg");
        file1.setRawFilename(resource1.getFilename());
        file1.setContent(IOUtils.toByteArray(resource1.getInputStream()));
        file2.setRawFilename(resource2.getFilename());
        file2.setContent(IOUtils.toByteArray(resource2.getInputStream()));
    	List<SimpleFile> files = Lists.newArrayList(file1, file2);
    	List<UploadInfo> infos = takaService.simpleUploadAll(files);
    	System.out.println(infos);
    }
    
    @Test
    public void testAdvancedUpload() throws Exception {
        AdvanceFile file = new AdvanceFile();
        ClassPathResource resource = new ClassPathResource("test.png");
        file.setRawFilename(resource.getFilename());
        file.setContent(IOUtils.toByteArray(resource.getInputStream()));
        file.setBucket("cc-files");
        file.setCustomPath("/dsp/creative/xxx.jpg");
        UploadInfo info = takaService.advancedUpload(file);
        System.out.println(info);
    }
    
    @Test
    public void testGenerateTimestamp() {
        String timestamp = TakaUtil.generateTimestamp();
        System.out.println(timestamp);
    }

    @Test
    public void testGetTokenUrl() {
        String timestamp = TakaUtil.generateTimestamp();
        // ugly interface...
        String content = this.accessKey + timestamp + this.secretKey + 0 + "" + 0 + "";
        String sign = EncryptUtils.encryptMD5(content);
        StringBuffer url = new StringBuffer(this.intranetUploadSite);
        url.append("/v2/token?accessKey=");
        url.append(this.accessKey);
        url.append("&timestamp=");
        url.append(timestamp);
        url.append("&sign=");
        url.append(sign);
        url.append("&uid=0&fileType=&maxSize=0&rawFileName=");
        System.out.println("Token URL:"+ url.toString());

    }
    
    @Test
    public void testGetUploadToken() throws Exception {
        QiniuProxy qiniuClient = new QiniuProxy(accessKey, secretKey, intranetUploadSite);
        String token = qiniuClient.getToken();
        System.out.println("Token:" + token);
    }

    @Test
    public void testUpload() {
    	String result = RestClient.getInstance().call(null, HttpMethod.POST, String.class);
    	System.out.println(result);
    }
}
