/**
 * 
 */
package com.hujiang.fileupload.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hujiang.basic.framework.plugin.dfs.model.AdvanceFile;
import com.hujiang.basic.framework.plugin.dfs.model.SimpleFile;
import com.hujiang.basic.framework.plugin.dfs.model.UploadInfo;
import com.hujiang.basic.framework.plugin.dfs.service.TakaService;
import com.hujiang.basic.framework.rest.model.DataResult;

/**
 * @author yangkai
 *
 */
@RestController
@RequestMapping("/")
public class FileController {

	@Autowired
	TakaService takaService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public DataResult<Object> ok() {
		
		return DataResult.ok();
	}
	
	@RequestMapping(value = "/upload/simple/all", method = RequestMethod.POST)
	public DataResult<Object> uploadAll(@RequestParam("files") MultipartFile[] files) throws Exception {
		List<SimpleFile> simpleFiles = new ArrayList<>();

		for (MultipartFile file : files) {
			SimpleFile simpleFile = new SimpleFile();
			simpleFile.setRawFilename(file.getOriginalFilename());
			simpleFile.setContent(file.getBytes());
			simpleFiles.add(simpleFile);
		}
		
		List<UploadInfo> infos = takaService.simpleUploadAll(simpleFiles);

		return DataResult.ok(infos);
	}
	
	@RequestMapping(value = "/upload/simple", method = RequestMethod.POST)
	public DataResult<Object> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
		SimpleFile simpleFile = new SimpleFile();
		simpleFile.setRawFilename(file.getOriginalFilename());
		simpleFile.setContent(file.getBytes());
		UploadInfo info = takaService.simpleUpload(simpleFile);
		return DataResult.ok(info);
	}

	@RequestMapping(value = "/upload/advance", method = RequestMethod.POST)
	public DataResult<Object> advanceUpload(String bucket, String customKey, String customPath, @RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
		AdvanceFile advanceFile = new AdvanceFile();
		advanceFile.setBucket(bucket);
		advanceFile.setRawFilename(file.getOriginalFilename());
		advanceFile.setContent(file.getBytes());
		advanceFile.setCustomKey(customKey);
		advanceFile.setCustomPath(customPath);
		UploadInfo info = takaService.advancedUpload(advanceFile);

		return DataResult.ok(info);
	}
	
	@RequestMapping(value = "/upload/all", method = RequestMethod.POST)
	public DataResult<Object> uploadAll(
			@RequestParam("images") MultipartFile[] images, 
			@RequestParam("docks")  MultipartFile[] docks) throws Exception {
		System.out.println(images);
		System.out.println(docks);
		return DataResult.ok(null);
	}

}
