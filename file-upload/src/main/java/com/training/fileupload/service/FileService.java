/**
 * 
 */
package com.training.fileupload.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import lombok.Data;

/**
 * @author yangkai
 *
 */
@Data
public class FileService {
	
	 protected String url;
	 
	    protected Map<String, String> headers;
	 
	    protected int connectionTimeout = 5000;
	 
	    protected int soTimeout = 10000;
	 
	    protected int statusCode = 200;
	 
	    protected String charset = HTTP.UTF_8;
	 
	    protected HttpGet httpGet;
	 
	    protected HttpPost httpPost;
	 
	    protected HttpParams httpParameters;
	 
	    protected HttpResponse httpResponse;
	 
	    protected HttpClient httpClient;
	 
	    protected String inputContent;
	    
	public FileService() {
	
	}
	
//	 public String post(String url, Map<String, String> datas, Map<String, String> files) throws IOException
//	    {
//	        this.setUrl(url);
//	        // 实例化 GET 连接
//	        this.httpPost = new HttpPost(this.url);
//	        // 自定义配置 header 信息
//	        this.addHeaders(this.httpPost);
//	        // 初始化客户端请求
//	        this.initHttpClient();
//	        Iterator iterator = datas.entrySet().iterator();
//	        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
//	        multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//	        multipartEntityBuilder.setCharset(Charset.forName(this.charset));
//	        // 发送的数据
//	        while (iterator.hasNext()) {
//	            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
//	            multipartEntityBuilder.addTextBody(entry.getKey(), entry.getValue(), ContentType.create(text/plain, Charset.forName(this.charset)));
//	        }
//	        // 发送的文件
//	        if (files != null) {
//	            iterator = files.entrySet().iterator();
//	            while (iterator.hasNext()) {
//	                Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
//	                String path = entry.getValue();
//	                if (.equals(path) || path == null) continue;
//	                File file = new File(entry.getValue());
//	                multipartEntityBuilder.addBinaryBody(entry.getKey(), file);
//	            }
//	        }
//	        // 生成 HTTP 实体
//	        HttpEntity httpEntity = multipartEntityBuilder.build();
//	        // 设置 POST 请求的实体部分
//	        this.httpPost.setEntity(httpEntity);
//	        // 发送 HTTP 请求
//	        this.httpResponse = this.httpClient.execute(this.httpPost);
//	        // 读取远程数据
//	        this.getInputStream();
//	        // 远程请求状态码是否正常
//	        if (this.statusCode != HttpStatus.SC_OK) {
//	            return null;
//	        }
//	        // 返回全部读取到的字符串
//	        return this.inputContent.toString();
//	    }

}
