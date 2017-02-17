/**
 * 
 */
package com.hujiang.fileupload;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.google.common.annotations.VisibleForTesting;

/**
 * @author yangkai
 *
 */
public class TestFileIUpload {

	/**
	 * 
	 */
	public TestFileIUpload() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void test() throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost("url");
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		File file = new File("d:/photo.jpg");
		httpPost.setHeader("User-Agent","SOHUWapRebot");
		httpPost.setHeader("Accept-Language","zh-cn,zh;q=0.5");
		httpPost.setHeader("Accept-Charset","GBK,utf-8;q=0.7,*;q=0.7");
		httpPost.setHeader("Connection","keep-alive");
		HttpEntity mutiEntity = MultipartEntityBuilder.create()
				.addPart("desc",new StringBody("美丽的西双版纳", ContentType.MULTIPART_FORM_DATA))
				.addPart("pic", new FileBody(file)).build();
		
		httpPost.setEntity(mutiEntity);
		
		HttpResponse  httpResponse = httpClient.execute(httpPost);
		HttpEntity httpEntity =  httpResponse.getEntity();
		String content = EntityUtils.toString(httpEntity);
	}

}
