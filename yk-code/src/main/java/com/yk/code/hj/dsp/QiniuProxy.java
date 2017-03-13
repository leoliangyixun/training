package com.yk.code.hj.dsp;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;

import com.hujiang.basic.framework.plugin.dfs.model.AdvanceFile2;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.TypeReference;
import com.hujiang.basic.framework.core.exception.AppException;
import com.hujiang.basic.framework.core.exception.SysException;
import com.hujiang.basic.framework.core.util.EncryptUtils;
import com.hujiang.basic.framework.core.util.HttpClientUtil;
import com.hujiang.basic.framework.core.util.JsonUtil;
import com.hujiang.basic.framework.plugin.dfs.model.AdvanceFile;
import com.hujiang.basic.framework.plugin.dfs.model.SimpleFile;
import com.hujiang.basic.framework.plugin.dfs.model.UploadInfo;
import com.hujiang.basic.framework.plugin.dfs.util.TakaUtil;
import com.hujiang.basic.framework.rest.model.DataResult;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Jonathan Zhang
 * created at: 12/12/2016
 */
@Slf4j
public class QiniuProxy implements TakaService {
	
    private String accessKey;
	
    private String secretKey;
	
    private String intranetUploadSite;

	
	public QiniuProxy(String accessKey, String secretKey, String intranetUploadSite) {
		this.accessKey = accessKey;
		this.secretKey = secretKey;
		this.intranetUploadSite = intranetUploadSite;
	}

	@Override
	public UploadInfo simpleUpload(SimpleFile file) {
		UploadInfo info = null;
		if (file != null) {
			String result = null;
			try {
	            HttpPost httpPost = new HttpPost(intranetUploadSite + "/v2/file");
	            HttpClient httpClient = HttpClientBuilder.create().build();
	            httpPost.addHeader("token", this.getToken());
	            //the filename is must.
	            HttpEntity mutiEntity = MultipartEntityBuilder.create().addBinaryBody("file", file.getContent(), ContentType.MULTIPART_FORM_DATA, file.getRawFilename()).build();
	            httpPost.setEntity(mutiEntity);
	            HttpResponse httpResponse = httpClient.execute(httpPost);
	            HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity);	
			} catch (ParseException | IOException e) {
				log.error("file upload failed, error stack:", e);
			}
			log.info("simple upload result: {}", result);
			DataResult<List<UploadInfo>> data = JsonUtil.json2Reference(result, new TypeReference<DataResult<List<UploadInfo>>>(){});
			if (data != null) {
                if (data.getStatus() != 0) {
                    throw new AppException(data.getStatus(), data.getMessage());
                }
                info = CollectionUtils.isNotEmpty(data.getData()) ? data.getData().get(0) : null;
            } else {
				throw new SysException(400, "unexpected error");
            }
        }
		
		return info;
	}

	@Override
	public List<UploadInfo> simpleUploadAll(List<SimpleFile> files) {
		List<UploadInfo> infos = null;
		if (CollectionUtils.isNotEmpty(files)) {
			String result = null;
			try {
				HttpPost httpPost = new HttpPost(intranetUploadSite + "/v2/file");
				HttpClient httpClient = HttpClientBuilder.create().build();
				httpPost.addHeader("token", this.getToken());
				// the filename is must.
				MultipartEntityBuilder builder = MultipartEntityBuilder.create();
				files.forEach(file -> builder.addBinaryBody("file", file.getContent(), ContentType.MULTIPART_FORM_DATA,
						file.getRawFilename()));
				HttpEntity mutiEntity = builder.build();
				httpPost.setEntity(mutiEntity);
				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity);
			} catch (ParseException | IOException e) {
				log.error("file upload failed, error stack:", e);
			}
			log.info("simple upload result: {}", result);
			DataResult<List<UploadInfo>> data = JsonUtil.json2Reference(result,
					new TypeReference<DataResult<List<UploadInfo>>>() {
					});
			if (data != null) {
				if (data.getStatus() != 0) {
					throw new AppException(data.getStatus(), data.getMessage());
				}
				infos = data != null && data.getStatus() == 0 ? data.getData() : null;
			} else {
				throw new SysException(400, "unexpected error");
			}
		}

		return infos;
	}

	@Override
	public UploadInfo advancedUpload(AdvanceFile file) {
		UploadInfo info = null;
		if (file != null) {
			String result = null;
			try {
				HttpPost httpPost = new HttpPost(intranetUploadSite + "/v2/file");
				HttpClient httpClient = HttpClientBuilder.create().build();
				httpPost.addHeader("token", this.getToken());
				// the filename is must.
				MultipartEntityBuilder builder = MultipartEntityBuilder.create();
				builder.addBinaryBody("file", file.getContent(), ContentType.MULTIPART_FORM_DATA, file.getRawFilename())
						.addTextBody("bucket", file.getBucket());
				// customKey已被dfs禁用
				/*
				 * if (StringUtils.isNotBlank(file.getCustomKey())) {
				 * 
				 * builder.addTextBody("customFileId", file.getCustomKey()); }
				 */

				if (StringUtils.isNotBlank(file.getCustomPath())) {
					builder.addTextBody("customFilePath", file.getCustomPath());
				}
				
				HttpEntity mutiEntity = builder.build();
				httpPost.setEntity(mutiEntity);
				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity);
			} catch (ParseException | IOException e) {
				log.error("advance upload failed, error stack:", e);
			}
			log.info("advance upload result: {}", result);
			DataResult<List<UploadInfo>> data = JsonUtil.json2Reference(result,
					new TypeReference<DataResult<List<UploadInfo>>>() {
					});

			if (data != null) {
				if (data.getStatus() != 0) {
					throw new AppException(data.getStatus(), data.getMessage());
				}
				info = data != null && data.getStatus() == 0 && CollectionUtils.isNotEmpty(data.getData())
						? data.getData().get(0) : null;
			} else {
				throw new SysException(400, "unexpected error");
			}
		}

		return info;
	}

	@Override
	public List<UploadInfo> advancedUpload2(AdvanceFile2 file) {
		List<UploadInfo> infos = null;
		if (file != null) {
			String result = null;
			try {
				HttpPost httpPost = new HttpPost(intranetUploadSite + "/v2/file");
				HttpClient httpClient = HttpClientBuilder.create().build();
				httpPost.addHeader("token", this.getToken());
				// the filename is must.
				MultipartEntityBuilder builder = MultipartEntityBuilder.create();
				file.getFiles().forEach(e -> {
					builder.addBinaryBody("file", e.getContent(), ContentType.MULTIPART_FORM_DATA, e.getRawFilename());
				});

				builder.addTextBody("bucket", file.getBucket());
				// customKey已被dfs禁用
				/*
				 * if (StringUtils.isNotBlank(file.getCustomKey())) {
				 *
				 * builder.addTextBody("customFileId", file.getCustomKey()); }
				 */



				HttpEntity mutiEntity = builder.build();
				httpPost.setEntity(mutiEntity);
				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity);
			} catch (ParseException | IOException e) {
				log.error("advance upload failed, error stack:", e);
			}
			log.info("advance upload result: {}", result);
			DataResult<List<UploadInfo>> data = JsonUtil.json2Reference(result,
					new TypeReference<DataResult<List<UploadInfo>>>() {
					});

			if (data != null) {
				if (data.getStatus() != 0) {
					throw new AppException(data.getStatus(), data.getMessage());
				}
				infos = data != null && data.getStatus() == 0 && CollectionUtils.isNotEmpty(data.getData())
						? data.getData() : null;
			} else {
				throw new SysException(400, "unexpected error");
			}
		}

		return infos;
	}

	public String getToken() {
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
		String result = HttpClientUtil.sendHttpGet(url.toString());
		DataResult<String> data = JsonUtil.json2Reference(result, new TypeReference<DataResult<String>>(){});
		
		return data.getData();
	}

	private Collection<SimpleFile> simpleFiles;
	private Collection<AdvanceFile2> simpleFiles;


	@Override
	public TakaService simple(SimpleFile file) {
		return null;
	}

	@Override
	public TakaService simpleAll(Collection<SimpleFile> files) {
		return null;
	}

	@Override
	public TakaService advance(AdvanceFile file) {
		return null;
	}

	@Override
	public TakaService advanceAll(Collection<AdvanceFile> files) {
		return null;
	}

	@Override
	public Collection<UploadInfo> simpleUpload() {
		return null;
	}

	@Override
	public Collection<UploadInfo> advanceUpload() {
		return null;
	}
}
