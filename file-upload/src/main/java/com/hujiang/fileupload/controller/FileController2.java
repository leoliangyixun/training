///**
// * 
// */
//package com.hujiang.fileupload.controller;
//
//import com.hujiang.basic.framework.core.util.EncryptUtils;
//import com.hujiang.basic.framework.rest.common.HttpClientUtil;
//import com.hujiang.basic.framework.rest.model.DataResult;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.util.EntityUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
///**
// * @author yangkai
// *
// */
//@RestController
//@RequestMapping("/v2")
//public class FileController2 {
//
//	public static final String accessKey = "HJTAKACCOMMON09jn8F893NHDFGNOASDGG";
//	public static final String secretKey = "BHKHDYI72395HKSDHGF97WY6923Y50ZDUOFUGN";
//	public static final String  intranetUploadSite = "http://qadfs.soa.yeshj.com";
//
//	public static final long TICKS_AT_EPOCH = 621355968000000000L;
//
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public DataResult<Object> ok() {
//		
//		return DataResult.ok();
//	}
//	
//	@RequestMapping(value = "/upload/all", method = RequestMethod.POST)
//	public DataResult<Object> uploadAll(@RequestParam("files") MultipartFile[] files) {
//		System.out.println(files);
//		return DataResult.ok();
//	}
//	
//	@RequestMapping(value = "/upload", method = RequestMethod.POST)
//	public DataResult<Object> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
//		
//		try {
//
//			//生成signature
//			String signature = this.generateSignature();
//			//请求token
//
//
//
//
//			HttpPost httpPost = new HttpPost(intranetUploadSite + "/v2/file");
//			
//			HttpClient httpClient = HttpClientBuilder.create().build();
//
////			String filePath = request.getSession().getServletContext().getRealPath("/") + "/upload/"
////					+ file.getOriginalFilename();
////			File f = new File(filePath);
////			file.transferTo(f);
//
//			httpPost.setHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36");
//            httpPost.setHeader("X-Machine-Name", "max os");
//            httpPost.setHeader("Access-Signature", this.generateSignature());
//			HttpEntity mutiEntity = MultipartEntityBuilder.create()
//					.addTextBody("bucket", "app")
//					.addBinaryBody("file", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, file.getOriginalFilename())
//					.build();
//			
//			httpPost.setEntity(mutiEntity);
//			
//			HttpResponse  httpResponse = httpClient.execute(httpPost);
//			HttpEntity httpEntity =  httpResponse.getEntity();
//			String content = EntityUtils.toString(httpEntity);
//			System.out.println(content);
//			//byte[] bits = StreamUtils.copyToByteArray(file.getInputStream());
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return DataResult.ok();
//	}
//	
//	public String generateSignature() {
////         Long timestamp = new Date().getTime();
////
////		 String sign = EncryptUtils.encryptMD5(accessKey + timestamp + secretKey + 0);
////		 String source = StringUtils.join(new Object[]{accessKey, timestamp, sign, 0},  ":");
////
////		 return new String(Base64.encodeBase64(source.getBytes()), "UTF-8");
////accessKey + this.generateTimestamp() + secretKey + 0 + "" + 0 +""
//		return EncryptUtils.encryptMD5(accessKey + this.generateTimestamp() + secretKey + 0 + "" + 0 +"");
//	}
//
//	public String generateTimestamp() {
//		long millis = System.currentTimeMillis() % 1000;
//		return String.valueOf(millis+TICKS_AT_EPOCH);
//	}
//
//	public String getToken() {
//
//		String sign = this.generateSignature();
//		String timestamp = this.generateTimestamp();
//
//		StringBuffer url = new StringBuffer(intranetUploadSite);
//		url.append("/v2/token?accessKey=");
//		url.append(accessKey);
//		url.append("&timestamp=");
//		url.append(timestamp);
//		url.append("&sign=");
//		url.append(sign);
//		url.append("&uid=0&fileType=&maxSize=0&rawFileName=");
//
//		String result = HttpClientUtil.sendHttpGet(url.toString());
//		System.out.println(result);
//		return null;
//
//	}
//
//	public static void main(String[] args) {
//		FileController2 c = new FileController2();
//
//		System.out.println(c.getToken());
//	}
//
//}
